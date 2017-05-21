import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * DateTime: 2016/9/20 19:16
 * 功能：
 * 封装的Map
 * 比如表单中新增的用户名 <input name=”username” value=”张三”>
 * 后台pd = this.getPageData();后，pd里面就有username的值了
 * 思路：
 */
public class PageData extends HashMap implements Map {
    //测试
    public static void main(String[] args) {

    }

    private static final long serialVersionUID=1L;

    Map map=null;
    HttpServletRequest request;

    public PageData(HttpServletRequest request){
        this.request=request;
        Map properties=request.getParameterMap();
        Map returnMap=new HashMap();

        Iterator entries=properties.entrySet().iterator();
        Entry entry;
        String name="";
        String value="";

        while ( entries.hasNext() ){
            entry=(Entry) entries.next();
            name= (String) entry.getKey();
            Object valueObj=entry.getValue();
            if(null==valueObj){
                value="";
            }else if ( valueObj instanceof String[] ){
                String[] values= (String[]) valueObj;
                for ( int i = 0; i < values.length; i++ ) {
                    value=values[i]+",";
                }
                value=value.substring(0,value.length()-1);
            }else {
                value=valueObj.toString();
            }
            returnMap.put(name,value);

        }
        map=returnMap;
    }

    public PageData(){
        map=new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj=null;
        if(map.get(key) instanceof Object[]){
            Object[] arr=(Object[]) map.get(key);
            obj=request==null?arr:(request.getParameter((String) key)==null?arr:arr[0]);
        }else {
            obj=map.get(key);
        }
        return obj;
    }


    public String getString(Object key){
        return (String) get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key,Object value){
        return map.put(key,value);
    }

    @Override
    public Object remove(Object key){
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @Override
    public Set entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void putAll(Map m) {
        map.putAll(m);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection values() {
        return map.values();
    }
}
