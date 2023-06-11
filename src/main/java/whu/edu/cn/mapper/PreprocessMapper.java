package whu.edu.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import whu.edu.cn.entity.Preprocess;

import java.util.List;
import java.util.Map;

@Mapper
public interface PreprocessMapper{
    //775547
    //1604029
    @Update("update ways set gid = gid - 1604029 + 775548 where gid = ${id}")
    void updateGidOfSichuan(Integer id);

    @Select("select gid, source, target from ways_sichuan order by gid asc")
    List<Preprocess> getAllSAT();

    @Select("select ways_all.gid as aid, ways_sichuan.gid as bid from ways_all, ways_sichuan \n" +
            "where ways_all.x1=ways_sichuan.x1 \n" +
            "and ways_all.x2=ways_sichuan.x2 \n" +
            "and ways_all.y1=ways_sichuan.y1 \n" +
            "and ways_all.y2=ways_sichuan.y2 \n" +
            "and ways_all.source_osm = ways_sichuan.source_osm \n" +
            "and ways_all.target_osm=ways_sichuan.target_osm \n" +
            "and ways_all.geohash=ways_sichuan.geohash\n" +
            "and ways_all.maxspeed_forward=ways_sichuan.maxspeed_forward\n" +
            "and ways_all.the_geom=ways_sichuan.the_geom\n" +
            "and ways_all.length=ways_sichuan.length\n" +
            "and ways_all.osm_id=ways_sichuan.osm_id")
    List<Map<Long, Long>> getGid();

    @Select("select ways_vertices_pgr_all.id as aid, ways_vertices_pgr_sichuan.id as bid \n" +
            "from ways_vertices_pgr_all, ways_vertices_pgr_sichuan \n" +
            "where ways_vertices_pgr_all.osm_id=ways_vertices_pgr_sichuan.osm_id")
    List<Map<Long, Long>> getId();

    @Update("update ways_all set source = ${source}, target = ${target} where gid = ${id}")
    void updateSAT(Long id, Long source, Long target);

}
