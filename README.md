##moho-commons

1 moho-commons-poi

1.1 excel操作工具(依赖poi.3.17版本):

    class: com.moho.commons.poi.ExcelReader 读取excel文件内容

           methods: public static Workbook getWorkbook(File file) 获取Workbook对象
                    param:
                        file:待读取的文件对象
                    return:
                        返回Workbook对象

           methods: public static Sheet getSheet(File file) 获取Sheet对象
                    param:
                        file:待读取的文件对象
                    return:
                        返回Sheet对象

           methods: public static Sheet getSheet(File file, Integer index) 获取Sheet对象
                    param:
                        file:待读取的文件对象
                        index:指定工作表
                    return:
                        返回Sheet对象

           methods: public static Sheet getSheet(File file, String sheetName) 获取Sheet对象
                    param:
                        file:待读取的文件对象
                        sheetName:指定工作表表名
                    return:
                        获取Sheet对象

           methods: public static Object getCellValue(Cell cell) 获取单元格值
                    param:
                        cell:cell对象
                    return:
                        返回单元格值


    
    class: com.moho.commons.poi.ExcelWriter 生成excel文件内容
    
           methods: public static <T> void generatorExcel(List<T> list, Class<T> clazz, String fileName) list集合写入Excel文档
                    param:
                        list:写入Excel文档的数据集合
                        clazz:待处理的对象
                        fileName:导出文件名
                    return:
                        无