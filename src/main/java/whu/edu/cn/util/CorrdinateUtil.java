package whu.edu.cn.util;

import org.apache.commons.lang3.ArrayUtils;
import org.postgis.LineString;
import org.postgis.LinearRing;
import org.postgis.Point;
import org.postgis.Polygon;
import whu.edu.cn.entity.Route;

/**
 * 坐标点格式化工具类
 */
public class CorrdinateUtil {
    /**
     * 格式化Polygon类型:
     * 将Vertexts字符串转换成Polygon类型
     * @param vertexes 多边形围栏形状点(顺时针或逆时针):   "double,double; double,double; ...;double,double"
     * @return Polygon
     */
    public static Polygon formatPolygon(String vertexes) throws Exception{
        vertexes = CorrdinateUtil.stringUtils(vertexes);
        String[] points = vertexes.split(";");
        int length = points.length;
        Point[] pointArray = new Point[length + 1];
        for (int i = 0;i<length;i++){
            String[] xy = points[i].split(",");
            pointArray[i] = new Point(Double.parseDouble(xy[0]),Double.parseDouble(xy[1]));
        }
        //首尾两点一致，封闭形成多边形
        String[] firstPoint = points[0].split(",");
        pointArray[length] = new Point(Double.parseDouble(firstPoint[0]),Double.parseDouble(firstPoint[1]));
        LinearRing linearRing = new LinearRing(pointArray);
        Polygon polygon = new Polygon(new LinearRing[]{linearRing});
        return polygon;
    }

    /**
     * 格式化Point类型:
     * 将Vertexts字符串转换成Point类型
     * @param vertexes 点坐标:"double,double"
     * @return Point
     */
    public static Point formatPoint(String vertexes) throws Exception{
        vertexes = CorrdinateUtil.stringUtils(vertexes);
        String[] split = vertexes.split(",");
        Point point = new Point(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        point.dimension = 2;
        //WGS84坐标系，也就是GPS使用的坐标
        point.srid = 4326;
        return point;
    }

    /**
     * 经纬度转为Point对象
     *
     * @param lnglat
     * @return
     */
    public static Point lnglatToPoint(Double[] lnglat) {
        if(ArrayUtils.isNotEmpty(lnglat)) {
            String vertexes = String.valueOf(lnglat[0])+","+String.valueOf(lnglat[1]);
            try {
                return CorrdinateUtil.formatPoint(vertexes);
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 格式化LineString类型：
     * 将Vertexts字符串转换成LineString类型
     * @param vertexes 折线点:   "double,double; double,double; ...;double,double"
     * @return LineString
     */
    public static LineString formatLineString(String vertexes) throws Exception{
        vertexes = CorrdinateUtil.stringUtils(vertexes);
        String[] points = vertexes.split(";");
        int length = points.length;
        Point[] pointArray = new Point[length];
        for (int i = 0;i<length;i++){
            String[] xys = points[i].split(",");
            pointArray[i] = new Point(Double.parseDouble(xys[0]),Double.parseDouble(xys[1]));
        }
        LineString lineString = new LineString(pointArray);
        return lineString;
    }

    //过滤""号
    public static String stringUtils(String str){
        return str.replace("\"","");
    }

    //获取点
    public static String getRouteLineString(Route route){
         return "a";
    }
}
