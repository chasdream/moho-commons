# moho-commons

1 moho-commons-poi:office操作

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

2 moho-commons-secret:加解密

2.1 AES加解密

    class:com.moho.commons.secret.AESUtils

          methods: public static String encrypt(String data, String key) aes加密方法
                   param:
                        data:待加密的数据
                        key:加密密钥key
                   return:
                        返回加密字符串

          methods: public static String decrypt(String data, String key) aes解密方法
                   param:
                        data:待解密数据
                        key:解密密钥key
                   return:
                        返回解密字符串
