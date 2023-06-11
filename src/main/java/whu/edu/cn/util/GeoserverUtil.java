package whu.edu.cn.util;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.coverage.GSCoverageEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSShapefileDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy.REPROJECT_TO_DECLARED;

public class GeoserverUtil {

    public static void main(String[] args) throws IOException {
        //GeoServer的连接配置
        String url = "http://125.220.153.25:8090/geoserver";
        String username = "admin";
        String password = "ypfamily608";

        String filePath = "C:\\Users\\dell\\Desktop\\nc_to_tif\\tif_result";
        ArrayList<String> es = new ArrayList<String>();
        File f = new File(filePath);
        File[] ts = f.listFiles();
        File[] fs = f.listFiles();
        for(int i = 0;i<fs.length;i++){
            if(ts[i].isFile()){
                es.add(ts[i].toString());
            }
        }
        for(int j=0;j<es.size();j++){
//        for(int j=0;j<2;j++){
            String store_name = es.get(j).substring(43, 47) + "-" + es.get(j).substring(47, 52) + es.get(j).substring(53, 57);
            String file_name = es.get(j);
            GeoserverPublishTiffData(url, username, password, store_name, file_name);
//            System.out.println(store_name);
//            System.out.println(file_name);
        }
//        String store_name = "2021-0718-0000";
//        String file_name = "\\mnt\\storage\\roadproject\\HenanGPM\\2021-0718-0500.tif";
//        String file_name = "C:\\Users\\dell\\Desktop\\nc_to_tif\\tif_rgb\\2021-0718-0000.tif";
//        GeoserverPublishTiffData(url, username, password, store_name, file_name);
//        GeoserverPublishShapefileData(url, username, password);  //发布shp数据
//        GeoserverPublishPostGISData(url, username, password);
    }

    /**
     * 发布tif格式数据
     * @param url
     * @param username
     * @param password
     * @throws IOException
     */
    private static void GeoserverPublishTiffData(String url, String username, String password, String store_name, String file_name)
            throws IOException {
        String workspace_name = "HenanGPM";
//        String store_name = "myFirstStoreOfTif";
//        String file_name = "D:\\dev\\Data\\栅格\\20210418-lfkfq【2m】\\GF6_PMS_E117.0_N39.5_20210418_L1A1120098132.tif";

        GeoServerRESTManager geoServerRESTManager = new GeoServerRESTManager(new URL(url), username, password);
        GeoServerRESTPublisher publisher = geoServerRESTManager.getPublisher();
        GeoServerRESTReader reader = geoServerRESTManager.getReader();
        /**
         * 获取reader 和 publisher的另一种方式
         * GeoServerRESTPublisher geoServerRESTPublisher = new GeoServerRESTPublisher(url, username, passwd);
         * GeoServerRESTReader geoServerRESTReader = new GeoServerRESTReader(url, username, passwd);
         *
         */
        // 检查workspace 若不存在，创建workspace
        List<String> workspaceNames = reader.getWorkspaceNames();
        if (!workspaceNames.contains(workspace_name)) {
            boolean publisherWorkspace = publisher.createWorkspace(workspace_name);
            System.out.println("create workspace_name : " + publisherWorkspace);
        } else {
            System.out.println("workspace已经存在了,workspace_name name :" + workspace_name);
        }
//        RESTDataStore restStore = reader.getDatastore(workspace_name, store_name);
//        if (restStore == null) {
//            GSGeoTIFFDatastoreEncoder gsGeoTIFFDatastoreEncoder = new GSGeoTIFFDatastoreEncoder(store_name);
//            gsGeoTIFFDatastoreEncoder.setWorkspaceName(workspace_name);
//            gsGeoTIFFDatastoreEncoder.setUrl(new URL("file:" + file_name));
//            boolean createStore = geoServerRESTManager.getStoreManager().create(workspace_name, gsGeoTIFFDatastoreEncoder);
//            System.out.println("create store (TIFF文件创建状态) : " + createStore);
//            boolean publish = geoServerRESTManager.getPublisher().publishGeoTIFF(workspace_name, store_name, new File(file_name));
//            System.out.println("publish (TIFF文件发布状态) : " + publish);
//        } else {
//            System.out.println("数据存储已经存在了,store:" + store_name);
//        }

        String srs = "EPSG";//SRS空间参考系统 crs坐标参考系统
        GSCoverageEncoder gsCoverageEncoder = new GSCoverageEncoder();
        gsCoverageEncoder.setName(store_name);
        gsCoverageEncoder.setTitle(store_name);
        gsCoverageEncoder.setSRS(srs);
        gsCoverageEncoder.setNativeFormat("GeoTIFF");
        gsCoverageEncoder.addSupportedFormats("GEOTIFF");
        gsCoverageEncoder.addKeyword("geoTiff");
        gsCoverageEncoder.addKeyword("WCS");
        gsCoverageEncoder.setNativeCRS(srs);
        gsCoverageEncoder.setRequestSRS(srs);
        gsCoverageEncoder.setResponseSRS(srs);
        gsCoverageEncoder.setProjectionPolicy(REPROJECT_TO_DECLARED);
        gsCoverageEncoder.setLatLonBoundingBox(-180, -90, 180, 90, "EPSG:4326");
        //创建栅格数据存储
        boolean createStore = geoServerRESTManager.getPublisher().createCoverage(workspace_name, store_name, gsCoverageEncoder);
        System.out.println("Coverage store " + createStore);

        //发布GeoTIFF（如果没有目标数据存储则会先自动创建该命名栅格数据存储再发布）
        boolean publish = geoServerRESTManager.getPublisher().publishGeoTIFF(workspace_name, store_name, store_name, new File(file_name), srs, REPROJECT_TO_DECLARED, "HenanGPM");
        System.out.println("publish Coverage " + publish);
    }

    /***
     * 发布shapefile数据
     * @param url
     * @param username
     * @param password
     * @throws IOException
     */
    public static void GeoserverPublishShapefileData(String url, String username, String password) throws IOException {
        String workspace_name = "myFirstWorkspaceOfShp";     //workspace名称
        String store_name = "myFirstStoreOfShp"; //store名称

        //压缩文件的完整路径
        File zipFile = new File("D:\\dev\\Data\\矢量\\191206-开发区建筑矢量图\\build.zip");
        String layer_name = "build";//图层名称

        GeoServerRESTManager manager = new GeoServerRESTManager(new URL(url), username, password);
        GeoServerRESTPublisher publisher = manager.getPublisher();
        GeoServerRESTReader reader = manager.getReader();

        if (! reader.existsWorkspace(workspace_name)) {
            boolean create_workspace = publisher.createWorkspace(workspace_name);
            System.out.println("create workspace : " + create_workspace);
        } else {
            System.out.println("workspace已经存在了,workspace_name :" + workspace_name);
        }
        //shp文件所在的位置
        String urlDatastore = "file://"+"D:\\dev\\Data\\矢量\\191206-开发区建筑矢量图\\build.shp";
        //判断数据存储（datastore）是否已经存在，不存在则创建
        URL shpFileUrl = new URL(urlDatastore);
        RESTDataStore restStore = reader.getDatastore(workspace_name, store_name);
        if (restStore == null) {
            //创建shape文件存储
            GSShapefileDatastoreEncoder store = new GSShapefileDatastoreEncoder(store_name, shpFileUrl);
            boolean createStore = manager.getStoreManager().create(workspace_name, store);
            System.out.println("create store : " + createStore);
        } else {
            System.out.println("数据存储已经存在了,store:" + store_name);
        }
        //判断图层是否已经存在，不存在则创建并发布
        RESTLayer layer = reader.getLayer(workspace_name, layer_name);
        if (layer == null) {
            //发布图层
            boolean publish = manager.getPublisher().publishShp(workspace_name, store_name, layer_name, zipFile,  GeoServerRESTPublisher.DEFAULT_CRS);
            System.out.println("publish : " + publish);
        } else {
            System.out.println("表已经发布过了,table:" + store_name);
        }
    }

    //发布postgis中的数据
    public static void GeoserverPublishPostGISData(String url, String username, String passwd) throws IOException {
        //postgis连接配置
        String postgisHost = "localhost";
        int postgisPort = 5432;//端口号
        String postgisUser = "postgres";//用户名
        String postgisPassword = "postgres";//用户密码
        String postgisDatabase = "sqlView";//数据库名称

        String ws = "testNew";     //待创建和发布图层的工作区名称workspace
        String store_name = "testGeoserver"; //待创建和发布图层的数据存储名称store
        String table_name = "roa_4m"; // 数据库要发布的表名称,后面图层名称和表名保持一致

        //判断工作区（workspace）是否存在，不存在则创建
        URL u = new URL(url);
        GeoServerRESTManager manager = new GeoServerRESTManager(u, username, passwd);
        GeoServerRESTPublisher publisher = manager.getPublisher();
        List<String> workspaces = manager.getReader().getWorkspaceNames();
        if (!workspaces.contains(ws)) {
            boolean createws = publisher.createWorkspace(ws);
            System.out.println("create ws : " + createws);
        } else {
            System.out.println("workspace已经存在了,ws :" + ws);
        }

        //判断数据存储（datastore）是否已经存在，不存在则创建
        RESTDataStore restStore = manager.getReader().getDatastore(ws, store_name);
        if (restStore == null) {
            GSPostGISDatastoreEncoder store = new GSPostGISDatastoreEncoder(store_name);
            store.setHost(postgisHost);//设置url
            store.setPort(postgisPort);//设置端口
            store.setUser(postgisUser);// 数据库的用户名
            store.setPassword(postgisPassword);// 数据库的密码
            store.setDatabase(postgisDatabase);// 那个数据库;
            store.setSchema("public"); //当前先默认使用public这个schema
            store.setConnectionTimeout(20);// 超时设置
            //store.setName(schema);
            store.setMaxConnections(20); // 最大连接数
            store.setMinConnections(1);     // 最小连接数
            store.setExposePrimaryKeys(true);
            boolean createStore = manager.getStoreManager().create(ws, store);
            System.out.println("create store : " + createStore);
        } else {
            System.out.println("数据存储已经存在了,store:" + store_name);
        }

        //判断图层是否已经存在，不存在则创建并发布
        RESTLayer layer = manager.getReader().getLayer(ws, table_name);
        if (layer == null) {
            GSFeatureTypeEncoder pds = new GSFeatureTypeEncoder();
            pds.setTitle(table_name);
            pds.setName(table_name);
            pds.setSRS("EPSG:4326");
            GSLayerEncoder layerEncoder = new GSLayerEncoder();
            boolean publish = manager.getPublisher().publishDBLayer(ws, store_name, pds, layerEncoder);
            System.out.println("publish : " + publish);
        } else {
            System.out.println("表已经发布过了,table:" + table_name);
        }
    }
}
