package whu.edu.cn.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.postgis.LineString;

/**
 * 线，子类
 * @author kevin
 *
 */
@MappedTypes(LineString.class)
public class LineStringTypeHandler extends AbstractGeometryTypeHandler<LineString> {

}
