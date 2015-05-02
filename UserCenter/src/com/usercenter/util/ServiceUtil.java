package com.usercenter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class ServiceUtil {

    // JAXB clss对应Context
    public static Map<String, JAXBContext>                  contextMap     = new HashMap<String, JAXBContext>();
    // Class Feild 对应字段类型
    public static HashMap<String, HashMap<String, Integer>> feildClasssMap = new HashMap<String, HashMap<String, Integer>>();

    /**
     * 获取Jaxb Context
     * 
     * @param clzz
     * @return
     */
    private static <T> JAXBContext getContext(Class<T> clzz) {

        if (!contextMap.containsKey(clzz.getName())) {
            synchronized (clzz.getName()) {
                if (!contextMap.containsKey(clzz.getName())) {
                    try {
                        contextMap.put(clzz.getName(), JAXBContext.newInstance(clzz));
                    } catch (JAXBException e) {
                    }
                }
            }

        }

        return contextMap.get(clzz.getName());
    }

    /**
     * 获取字段类型
     * 
     * @param cls
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static HashMap<String, Integer> getFieldTypeMap(Class cls) {
        if (!feildClasssMap.containsKey(cls.getName())) {
            synchronized (cls.getName()) {
                if (!feildClasssMap.containsKey(cls.getName())) {

                    Field[] fields = cls.getDeclaredFields();

                    HashMap<String, Integer> clMap = new HashMap<String, Integer>();
                    Class cl = null;
                    for (int i = 0; i < fields.length; i++) {
                        cl = fields[i].getType();
                        if (cl == String.class) {
                            clMap.put(fields[i].getName(), 0);
                        } else if (cl == Integer.class || cl == int.class) {
                            clMap.put(fields[i].getName(), 1);
                        } else if (cl == Double.class || cl == double.class) {
                            clMap.put(fields[i].getName(), 2);
                        } else if (cl == Boolean.class || cl == boolean.class) {
                            clMap.put(fields[i].getName(), 3);
                        }
                    }
                    feildClasssMap.put(cls.getName(), clMap);

                }
            }
        }
        return feildClasssMap.get(cls.getName());
    }

    public static Map<String, String> createMap(MultivaluedMap<String, String> multivaluedMap) {
        Map<String, String> retMap = new HashMap<String, String>();
        Set<String> keySet = multivaluedMap.keySet();
        for (String key : keySet) {
            String value = multivaluedMap.get(key).get(0);
            retMap.put(key, value);
        }
        return retMap;
    }



    /**
     * convert xml string to Java object by JAXB.
     * @param obj  to convert java object.
     * @param xmlStr    
     * @return
     */
    public static <T> T convertXmlStr2Java(Class<T> clzz, String xmlStr) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(clzz);
            InputStream source = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            JAXBElement<T> element = unmarshaller.unmarshal(new StreamSource(source), clzz);
            source.close();
            return element.getValue();

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * convert xml string to Java object by JAXB.
     * Optimization, cache JAXBContext 
     * @param obj  to convert java object.
     * @param xmlStr    
     * @return
     */
    public static <T> T convertXmlStr2Java2(Class<T> clzz, String xmlStr) {
        try {
            JAXBContext ctx = getContext(clzz);
            InputStream source = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            JAXBElement<T> element = unmarshaller.unmarshal(new StreamSource(source), clzz);
            source.close();
            return element.getValue();

            //            return (T) unmarshaller.unmarshal(new StringReader(xmlStr));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * convert xml string to Java object by JAXB.
     * For simple objects
     * @param obj  to convert java object.
     * @param xmlStr    
     * @return
     */
    public static <T> T convertXmlStr2JavaPojo(T t, String xmlStr) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            String valueStr = null;
            String fieldName = null;
            HashMap<String, Integer> cMap = getFieldTypeMap(t.getClass());
            for (int i = 0; i < fields.length; i++) {
                fieldName = fields[i].getName();

                int keyindex = xmlStr.indexOf("<" + fieldName + ">");
                if (keyindex != -1) {
                    valueStr = xmlStr.substring(keyindex + fieldName.length() + 2,
                        xmlStr.indexOf("</" + fieldName + ">"));
                    setFieldValue(t, fields[i], cMap.get(fieldName), valueStr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * setObject
     * 
     * @param obj
     * @param field
     * @param value
     */
    public static void setFieldValue(Object obj, Field field, Integer key, String valueStr) {

        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object value = null;
            switch (key) {
                case 0:
                    value = valueStr;
                    break;
                case 1:
                    if (valueStr.length() > 0)
                        value = Integer.valueOf(valueStr);
                    break;
                case 2:
                    if (valueStr.length() > 0)
                        value = Double.valueOf(valueStr);
                    break;
                case 3:
                    if (valueStr.length() > 0)
                        value = Boolean.valueOf(valueStr);
                    break;
                default:
                    break;
            }
            field.set(obj, value);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * convent java object to xml format String.
     * 
     * @param originalObj
     * @param xmlCharset
     *            the format of charset for xml. ie "UTF-8", "GBK"
     * @param isFragment
     *            whether or not display the header for the generated xml. such
     *            as <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     * @return
     */
    public static String convertJava2XmlStr(Object originalObj, String xmlCharset,
                                            boolean isFragment) {
        String xmlStr = "";
        try {
            //            JAXBContext ctx = JAXBContext.newInstance(originalObj.getClass());
            JAXBContext ctx = getContext(originalObj.getClass());
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, xmlCharset);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, isFragment);
            marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                new CharacterEscapeHandler() {
                    public void escape(char[] ch, int start, int length, boolean isAttVal,
                                       Writer writer) throws IOException {
                        writer.write(ch, start, length);
                    }
                });
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            marshaller.marshal(originalObj, os);

            xmlStr = os.toString("UTF-8");
            os.close();
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlStr;
    }

    public static String object2JsonStr(Object o) {

        return JSONObject.fromObject(o).toString();
    }

    public static JSONObject object2Json(Object o) {

        return JSONObject.fromObject(o);
    }

    public static Object jsonStr2Object(String jsonStr, Object o, Map<String, Class> classMap) {

        return JSONObject.toBean(JSONObject.fromObject(jsonStr), o.getClass(), classMap);
    }

    public static Object jsonStr2Object(String jsonStr, Class clazz, Map<String, Class> classMap) {

        return JSONObject.toBean(JSONObject.fromObject(jsonStr), clazz, classMap);
    }

    /**
     * 
     * 
     * @param jsonString
     * @param clazz
     * @param classMap  
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T[] getDTOArray2(Object jsonString, Class clazz, Map<String, Class> classMap) {
        JSONArray array = JSONArray.fromObject(jsonString);

        if (classMap != null) {
            Object obj1 = JSONArray.toArray(array, clazz, classMap);
            return (T[]) obj1;
        }

        Object[] obj = new Object[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            obj[i] = JSONObject.toBean(jsonObject, clazz);
        }
        return (T[]) obj;
    }
}
