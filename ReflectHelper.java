import java.lang.reflect.Field;

/**
 * DateTime: 2016/9/20 17:28
 * 功能：反射工具
 * 思路：
 */
public class ReflectHelper {
    //测试
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Demodemo demodemo=new Demodemo();
        System.out.println(getFieldByFieldName(demodemo,"name"));
        System.out.println(getValueByFieldName(demodemo,"name"));
        demodemo.setName("YEN");
        System.out.println(getValueByFieldName(demodemo,"name"));
        setValueByFieldName(demodemo,"name","LMC");
        System.out.println(getValueByFieldName(demodemo,"name"));
    }

    /**
     * 获取object对象fieldName的Field
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getFieldByFieldName(Object obj,String fieldName){
        for ( Class<?> superClass = obj.getClass();superClass!=Object.class;superClass=superClass.getClass() ){
            try {
                return superClass.getDeclaredField(fieldName);
            } catch ( NoSuchFieldException e ) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *获取obj对象fieldName的属性值
     * @param object
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     */
    public static Object getValueByFieldName(Object object,String fieldName) throws IllegalAccessException {
        Field field=getFieldByFieldName(object,fieldName);
        Object value=null;
        if(null!=field){
            if(field.isAccessible()){
                value=field.get(object);
            }else {
                field.setAccessible(true);  //如果字段是私有的,那么必须要对这个字段设置field.setAccessible(true) 这样才可以正常使用
                value=field.get(object);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 设置obj对象fieldName的属性值
     * @param object
     * @param fieldName
     * @param value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setValueByFieldName(Object object,String fieldName,Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field=object.getClass().getDeclaredField(fieldName);
        if(field.isAccessible()){
            field.set(object,value);
        }else {
            field.setAccessible(true);
            field.set(object,value);
            field.setAccessible(false);
        }
    }

}
