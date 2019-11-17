package str;

import java.lang.reflect.Field;

/**
 * Created by fubin on 2019-11-17.
 *
 * 数据库下划线转DTO驼峰
 */
public class FieldUnderlineConvertHump {
    public static void main(String[] args) {
        Field[] fields = TestDTO.class.getDeclaredFields();
        for(Field field:fields){
            String fieldName = field.getName();
            String lowerCaseName = fieldName.toLowerCase();
            StringBuffer strBuf = new StringBuffer();
            for(int i = 0 ; i < lowerCaseName.length() ; i++){
                if(lowerCaseName.charAt(i) == '_'){
                    String x = lowerCaseName.charAt(i+1)+"";
                    String X = x.toUpperCase();
                    strBuf.append(X);
                    i++;//跳过下一个字符
                }else{
                    strBuf.append(lowerCaseName.charAt(i));
                }
            }
            System.out.println("private String "+ strBuf.toString() +";");
        }
    }
}

class TestDTO {
    private String field1;
    private String field2;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
