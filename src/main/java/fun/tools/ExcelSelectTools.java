package fun.tools;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

public  class ExcelSelectTools {
    /**
     * 设置课程性质下拉列表（增强版：支持搜索）
     */
    public static void setupCourseNatureDropdown(Workbook workbook, Sheet mainSheet, int firstRow, int lastRow, String[] options, int columnIndex, boolean iskong) {
        try {
            // 如果选项数量较多（比如超过20个），添加搜索功能

            // 创建支持搜索的下拉列表
            createSearchableDropdown(workbook, mainSheet, options, firstRow, lastRow, columnIndex, iskong);


        } catch (Exception e) {
            System.err.println("设置下拉列表失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建支持搜索的下拉列表
     */
    public static void createSearchableDropdown(Workbook workbook, Sheet mainSheet, String[] options,
                                          int firstRow, int lastRow, int columnIndex, boolean iskong) {
        try {
            long l = System.currentTimeMillis();
            // 1. 创建隐藏工作表存储所有选项
            String hiddenSheetName = "SearchData_" + l;
            Sheet hiddenSheet = workbook.createSheet(hiddenSheetName);

            // 2. 写入选项数据（包含搜索关键词）
            writeSearchOptionsToHiddenSheet(hiddenSheet, options);

            // 3. 设置工作表隐藏
            workbook.setSheetHidden(workbook.getSheetIndex(hiddenSheet), true);

            // 4. 在主工作表创建搜索区域
            int searchAreaCol = findEmptyColumn(mainSheet, 50); // 从第10列开始找空列
//            setupSearchArea(mainSheet, searchAreaCol, options.length);

            // 5. 创建动态命名区域（根据搜索条件过滤）
            createDynamicNamedRange(workbook, hiddenSheet, searchAreaCol, options.length,l);

            // 6. 创建数据验证（使用动态命名区域）
            createDynamicDataValidation(mainSheet, firstRow, lastRow, columnIndex, iskong, searchAreaCol,l);

//            // 7. 添加使用说明
//            addSearchInstructions(mainSheet, searchAreaCol);

            System.out.println("创建支持搜索的下拉列表成功，搜索区域位于列: " + (searchAreaCol + 1));

        } catch (Exception e) {
            System.err.println("创建可搜索下拉列表失败: " + e.getMessage());

        }
    }

    /**
     * 写入搜索选项到隐藏工作表（包含原始值和搜索关键词）
     */
    public static void writeSearchOptionsToHiddenSheet(Sheet hiddenSheet, String[] options) {
        // 第一行写标题
        Row headerRow = hiddenSheet.createRow(0);
        headerRow.createCell(0).setCellValue("显示文本");
        headerRow.createCell(1).setCellValue("搜索关键词");
        headerRow.createCell(2).setCellValue("原始值");

        for (int i = 0; i < options.length; i++) {
            Row row = hiddenSheet.createRow(i + 1);
            String option = options[i];

            // 单元格0: 显示文本（原样显示）
            row.createCell(0).setCellValue(option);

            // 单元格1: 搜索关键词（去除特殊字符，便于搜索）
            String searchKey = createSearchKey(option);
            row.createCell(1).setCellValue(searchKey);

            // 单元格2: 原始值（用于返回）
            row.createCell(2).setCellValue(option);

            // 设置样式
            CellStyle style = hiddenSheet.getWorkbook().createCellStyle();
            style.setWrapText(true);
            for (int j = 0; j < 3; j++) {
                row.getCell(j).setCellStyle(style);
            }
        }

        // 调整列宽
        hiddenSheet.autoSizeColumn(0);
        hiddenSheet.autoSizeColumn(1);
        hiddenSheet.autoSizeColumn(2);
    }

    /**
     * 创建搜索关键词（去除ID等干扰信息）
     */
    public static String createSearchKey(String option) {
        // 示例：如果选项格式是 "ID-名称"，则提取名称部分作为搜索关键词
        if (option.contains("-")) {
            String[] parts = option.split("-", 2);
            if (parts.length > 1) {
                return parts[1].toLowerCase().replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", "");
            }
        }
        return option.toLowerCase().replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", "");
    }

    /**
     * 查找空列用于放置搜索区域
     */
    public static int findEmptyColumn(Sheet sheet, int startCol) {
        for (int col = startCol; col < startCol + 10; col++) {
            boolean isEmpty = true;
            for (int row = 0; row < Math.min(10, sheet.getLastRowNum() + 1); row++) {
                Row currentRow = sheet.getRow(row);
                if (currentRow != null && currentRow.getCell(col) != null) {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                return col;
            }
        }
        return startCol; // 如果没找到空列，使用起始列
    }

    /**
     * 设置搜索区域
     */
    public static void setupSearchArea(Sheet sheet, int searchAreaCol, int optionCount) {
        // 搜索标题行
        Row titleRow = sheet.createRow(1);
        if (titleRow == null) {
            titleRow = sheet.createRow(0);
        }

        // 搜索框标签
        Cell searchLabel = titleRow.createCell(searchAreaCol);
        searchLabel.setCellValue("搜索筛选:");

        // 搜索输入框
        Cell searchInput = titleRow.createCell(searchAreaCol + 1);
        searchInput.setCellValue(""); // 用户在这里输入搜索词

        // 设置搜索框样式
        CellStyle labelStyle = sheet.getWorkbook().createCellStyle();
        labelStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        labelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        searchLabel.setCellStyle(labelStyle);

        CellStyle inputStyle = sheet.getWorkbook().createCellStyle();
        inputStyle.setBorderBottom(BorderStyle.THIN);
        inputStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        searchInput.setCellStyle(inputStyle);
    }

    /**
     * 创建动态命名区域（根据搜索条件过滤）
     */
    public static void createDynamicNamedRange(Workbook workbook, Sheet hiddenSheet, int searchAreaCol, int optionCount,Long l) {
        try {
            String hiddenSheetName = hiddenSheet.getSheetName();
            int searchInputRow = 0; // 搜索输入框在第1行
            int searchInputCol = searchAreaCol + 1; // 搜索输入框在搜索区域列的下一列

            // 删除已存在的同名区域
            String[] rangeNames = {"FilteredOptions", "SearchResults"};
            for (String rangeName : rangeNames) {
                Name existingName = workbook.getName(rangeName);
                if (existingName != null) {
                    workbook.removeName(existingName);
                }
            }

            // 创建过滤后的选项区域
            Name filteredRange = workbook.createName();
            filteredRange.setNameName("FilteredOptions" +"_" + l);

            // 使用公式动态过滤：根据搜索关键词匹配
            String filterFormula = String.format(
                    "OFFSET('%s'!$A$2,0,0,COUNTIF('%s'!$B$2:$B$%d,\"*\"&%s!%s&\"*\"),1)",
                    hiddenSheetName, hiddenSheetName, optionCount + 1,
                    hiddenSheet.getWorkbook().getSheetName(0), // 主工作表名称
                    getCellReference(searchInputRow, searchInputCol)
            );
            filteredRange.setRefersToFormula(filterFormula);

        } catch (Exception e) {
            System.err.println("创建动态命名区域失败: " + e.getMessage());
            throw new RuntimeException("创建动态命名区域失败", e);
        }
    }

    /**
     * 创建动态数据验证
     */
    public static void createDynamicDataValidation(Sheet sheet, int firstRow, int lastRow, int columnIndex, boolean iskong, int searchAreaCol,Long l) {
        try {
            DataValidationHelper validationHelper = sheet.getDataValidationHelper();

            // 设置验证范围
            CellRangeAddressList addressList = new CellRangeAddressList(
                    firstRow, lastRow, columnIndex, columnIndex);

            // 使用动态命名区域
            DataValidationConstraint constraint = validationHelper.createFormulaListConstraint("FilteredOptions" + "_" + l);

            // 创建数据验证
            DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);

            // 设置验证属性
            setupSearchValidationProperties(dataValidation, iskong, searchAreaCol);

            // 添加到工作表
            sheet.addValidationData(dataValidation);

        } catch (Exception e) {
            System.err.println("创建动态数据验证失败: " + e.getMessage());
            throw new RuntimeException("创建动态数据验证失败", e);
        }
    }

    /**
     * 设置搜索验证属性
     */
    public static void setupSearchValidationProperties(DataValidation dataValidation, boolean iskong, int searchAreaCol) {
        dataValidation.setShowPromptBox(true);
        dataValidation.createPromptBox("（支持搜索）","请从下拉列表中选择");

        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        dataValidation.createErrorBox("无效输入", "请从下拉列表中选择有效选项！\n提示：可在单元格中输入关键词进行筛选");

        dataValidation.setShowErrorBox(true);
        dataValidation.setEmptyCellAllowed(iskong);
    }

    /**
     * 添加搜索使用说明
     */
    public static void addSearchInstructions(Sheet sheet, int searchAreaCol) {
        int instructionRow = 2; // 说明文字放在第3行

        Row row = sheet.getRow(instructionRow);
        if (row == null) {
            row = sheet.createRow(instructionRow);
        }

        Cell instructionCell = row.createCell(searchAreaCol);
        instructionCell.setCellValue("使用说明：在" + getColumnName(searchAreaCol + 1) +
                "1单元格输入关键词，下拉列表会自动筛选匹配的选项");

        // 设置说明文字样式
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(createInstructionFont(sheet.getWorkbook()));
        instructionCell.setCellStyle(style);
    }

    /**
     * 创建说明文字字体
     */
    public static Font createInstructionFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        font.setItalic(true);
        font.setFontHeightInPoints((short) 10);
        return font;
    }

    /**
     * 工具方法：获取单元格引用（如A1、B2）
     */
    public static String getCellReference(int row, int col) {
        return getColumnName(col) + (row + 1);
    }

    /**
     * 工具方法：获取列名（如A、B、AA）
     */
    public static  String getColumnName(int columnIndex) {
        StringBuilder columnName = new StringBuilder();
        while (columnIndex >= 0) {
            int remainder = columnIndex % 26;
            columnName.insert(0, (char) (remainder + 'A'));
            columnIndex = (columnIndex / 26) - 1;
        }
        return columnName.toString();
    }

}
