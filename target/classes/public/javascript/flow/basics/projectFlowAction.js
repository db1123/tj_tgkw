function updateWorkTime (xmlStr, startTime) {
  var XML = $.parseXML(xmlStr);
  // 最终日期
  var EndD = startTime;
  // 待执行节点
  var nowNodeList = [];
  // 获取所有开始标签
  $(XML).find('mxCell[simulationId="start"]').each(function() {
    var startNode = $(this);
    // 初始化开始节点起始日期
    startNode.attr('start_date', startTime);
    startNode.attr('end_date', startTime);
    // 获取所有以当前节点为源的edge
    $(XML).find('mxCell[edge="1"][source="'+startNode.attr('id')+'"]').each(function() {
      var edge = $(this);
      // 获取目标节点
      var targetNode;
      $(XML).find('mxCell[id="'+edge.attr('target')+'"]').each(function() {
        targetNode = $(this);
      });
      // 判断是否在数组中出现,未出现的加入数组
      var op = true;
      for (var i=0; i<nowNodeList.length; i++) {
        if(nowNodeList[i].attr('id') == targetNode.attr('id')) {
          op = false;
          break;
        }
      }
      if (op) {
        if (targetNode.attr('simulationId') && targetNode.attr('simulationId') != 'stop') {
          targetNode.attr('start_date', '');
          targetNode.attr('end_date', '');
          nowNodeList.push(targetNode);
        }
      }
    });
    // $(this).append('<a></a>')
    // $(this).attr('startTime', '111');
    // $(this).attr('endTime', '999');
  });
  // 获取节点
  while(nowNodeList.length > 0) {
    var tempArray = []; // 临时节点数组
    var waitNodes = {}; // 等待队列
    // 遍历所有待处理节点
    for (var i=0; i<nowNodeList.length; i++) {
      // 第一步
      // 是否进入第二步
      var opSecond = true;
      // 初始化最终开始时间
      var tempStartTime = '';
      // 获取所有以当前节点为目标的线
      $(XML).find('mxCell[edge="1"][target="'+nowNodeList[i].attr('id')+'"]').each(function() {
        var edge = $(this);
        // 获取源节点
        var sourceNode;
        $(XML).find('mxCell[id="'+edge.attr('source')+'"]').each(function() {
          sourceNode = $(this);
        });
        // 生成最终开始时间
        if (sourceNode) {
          if (sourceNode.attr('end_date') && sourceNode.attr('end_date') != '') {
            // 源节点有结束日期
            if (tempStartTime == '') {
              tempStartTime = sourceNode.attr('end_date');
            } else {
              // 判断是否超过当前时间
              if (!bjDate(tempStartTime, sourceNode.attr('end_date'))) {
                tempStartTime = sourceNode.attr('end_date');
              }
            }
          } else {
            // 无结束日期
            opSecond = false;
            throw Error();
          }
        }
      });
      // 判断是否进入第二步
      if (opSecond) {
        // 第二步
        // 计算结束时间
        var tempEndTime = '';
        if (tempStartTime == '') {
          // 判断是否处于等待队列
          if (waitNodes[nowNodeList[i].attr('id')]) {
            waitNodes[nowNodeList[i].attr('id')] = waitNodes[nowNodeList[i].attr('id')] - 1;
          } else {
            // 进入等待队列
            waitNodes[nowNodeList[i].attr('id')] = 20;
          }
          // 判断是否可以继续等待
          if (waitNodes[nowNodeList[i].attr('id')] > 0) {
            // 原始节点重新加入数组
            nowNodeList[i].attr('start_date', '');
            nowNodeList[i].attr('end_date', '');
            tempArray.push(nowNodeList[i]);
          }
        } else {
          // 判断是否存在子流程
          var subprocess;
          nowNodeList[i].find('Object[as="subprocess"]').each(function() {
            subprocess = $(this);
          });
          if (subprocess && subprocess.attr('subprocessXML')) {
            // 存在子流程
            var vl = updateWorkTime(subprocess.attr('subprocessXML'), tempStartTime);
            // 写入属性
            nowNodeList[i].attr('start_date', tempStartTime);
            nowNodeList[i].attr('end_date', vl.EndD);
            subprocess.attr('subprocessXML', mxUtils.getXml(vl.XML.documentElement));
          } else {
            // 获取工作周期
            var work_num = nowNodeList[i].attr('work_num');
            if (!work_num || work_num == '') {
              work_num = 1;
            }
            // 如果开始时间已经生成则进行计算
            tempEndTime = addDDate(tempStartTime, work_num);
            // 判断是否超过最终时间
            if (bjDate(tempEndTime, EndD)) {
              EndD = tempEndTime;
            }
            // 写入属性
            nowNodeList[i].attr('start_date', tempStartTime);
            nowNodeList[i].attr('end_date', tempEndTime);
          }
          // 获取所有以当前节点为源的edge
          $(XML).find('mxCell[edge="1"][source="'+nowNodeList[i].attr('id')+'"]').each(function() {
            var edge = $(this);
            // 获取目标节点
            var targetNode;
            $(XML).find('mxCell[id="'+edge.attr('target')+'"]').each(function() {
              targetNode = $(this);
            });
            // 目标节点加入数组
            if (targetNode.attr('simulationId') && targetNode.attr('simulationId') != 'stop') {
              targetNode.attr('start_date', '');
              targetNode.attr('end_date', '');
              tempArray.push(targetNode);
            }
          });
        }
      } else {
        // 判断是否处于等待队列
        if (waitNodes[nowNodeList[i].attr('id')]) {
          waitNodes[nowNodeList[i].attr('id')] = waitNodes[nowNodeList[i].attr('id')] - 1;
        } else {
          // 进入等待队列
          waitNodes[nowNodeList[i].attr('id')] = 20;
        }
        // 判断是否可以继续等待
        if (waitNodes[nowNodeList[i].attr('id')] > 0) {
          // 原始节点重新加入数组
          nowNodeList[i].attr('start_date', '');
          nowNodeList[i].attr('end_date', '');
          tempArray.push(nowNodeList[i]);
        }
      }
    }
    // 重新生成待执行列表
    nowNodeList = tempArray;
  }
  // 返回计算结果
  return {XML: XML, EndD: EndD};
}