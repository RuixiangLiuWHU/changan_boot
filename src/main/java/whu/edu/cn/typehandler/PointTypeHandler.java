package whu.edu.cn.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.postgis.Point;

/**
 * 点，子类
 * @author kevin
 *
 */

@MappedTypes(Point.class)
public class PointTypeHandler extends AbstractGeometryTypeHandler<Point> {

}
