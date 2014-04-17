<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=7, IE=9, IE=10">
    <!--The viewport meta tag is used to improve the presentation and behavior
    of the samples on iOS devices-->
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no">
    <title>OpenGeo WMTS Service</title>

    <link rel="stylesheet" href="http://serverapi.arcgisonline.com/jsapi/arcgis/3.5/js/dojo/dijit/themes/claro/claro.css">
    <link rel="stylesheet" href="http://serverapi.arcgisonline.com/jsapi/arcgis/3.5/js/esri/css/esri.css">
    <style>
      html, body { height: 100%; width: 100%; margin: 0; padding: 0; }
      #map{
        padding:0;
      }
      #feedback {
        background: #fff;
        border: 2px solid #666;
        border-radius: 5px;
        bottom: 20px;
        color: #666;
        font-family: arial;
        height: auto;
        left: 20px;
        margin: 5px;
        padding: 10px;
        position: absolute;
        width: 300px;
        z-index: 40;
      }
      #feedback a {
        border-bottom: 1px solid #888;
        color: #666;
        text-decoration: none;
      }
      #feedback a:hover, #feedback a:active, #feedback a:visited {
        border: none;
        color: #666;
        text-decoration: none;
      }
      #note { padding: 0 0 10px 0; }
      #info { padding: 10px 0 0 0; }
      <!--#divBlock{
      	position: relative;
      	margin-top:150;
      }-->
    </style>
    <script>var dojoConfig = {parseOnLoad: true};</script>
    <script src="http://serverapi.arcgisonline.com/jsapi/arcgis/3.5/"></script>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.Jcrop.js"></script>
	<!--<script type="text/javascript" language="Javascript" src="./TianDiTuTileMapServiceLayer.js"></script>-->
    <script>
      dojo.require("esri.map");
    dojo.require("esri.InfoTemplate");
    dojo.require("esri.layers.FeatureLayer");
    dojo.require("esri.tasks.query");
    dojo.require("esri.toolbars.draw");
    dojo.require("esri.tasks.geometry");
    dojo.require("esri.dijit.OverviewMap");
    dojo.require("esri.dijit.Scalebar");
    dojo.require("esri.dijit.Print");
    dojo.require("dijit.layout.BorderContainer");
    dojo.require("dijit.layout.ContentPane");
    dojo.require("dijit.form.Button");
    dojo.require("dojo._base.array");
    dojo.require("dojo.dom");
	  function initLayer() {
	  dojo.declare("TianDiTuLayer", esri.layers.TiledMapServiceLayer, {
		m_baseURL: null,
		m_serviceMode: null,
		m_imageFormat: null,
		m_tileMatrixSetId :null,
		m_layerId: null,
          constructor: function(p_baseURL,p_serviceMode,p_imageFormat,p_tileMatrixSetId,p_layerId) {
		  this.m_baseURL = p_baseURL;
		  this.m_serviceMode = p_serviceMode;
		  this.m_imageFormat = p_imageFormat;
		  this.m_tileMatrixSetId = p_tileMatrixSetId;
		  this.m_layerId = p_layerId;
            this.spatialReference = new esri.SpatialReference({
              wkid: 4490
            });
            this.initialExtent = new esri.geometry.Extent(70.0, 15.0, 135.0, 55.0, this.spatialReference);
            this.fullExtent = new esri.geometry.Extent(-180, -90, 180, 90, this.spatialReference);
            
            this.tileInfo = new esri.layers.TileInfo({
              "dpi": "96",
              "format": "tiles",
              "compressionQuality": 0,
              "spatialReference": {
                "wkid": "4490"
              },
              "rows": 256,
              "cols": 256,
              "origin": {
                "x": -180,
                "y":  90
              },

              // Scales in DPI 96
              "lods": [{
                "level": 0,
                "scale": 400000000,
                "resolution": 1.40625
              }, {
                "level": 1,
                "scale": 295497598.5708346,
                "resolution":  0.703125
              }, {
                "level": 2,
                "scale":  147748799.285417,
                "resolution": 0.3515625
              }, {
                "level": 3,
                "scale": 73874399.6427087,
                "resolution": 0.17578125
              }, {
                "level": 4,
                "scale": 36937199.8213544,
                "resolution": 0.087890625
              }, {
                "level": 5,
                "scale":  18468599.9106772,
                "resolution": 0.0439453125
              }, {
                "level": 6,
                "scale": 9234299.95533859,
                "resolution": 0.02197265625
              }, {
                "level": 7,
                "scale": 4617149.97766929,
                "resolution": 0.010986328125
              }, {
                "level": 8,
                "scale": 2308574.98883465,
                "resolution": 0.0054931640625
              }, {
                "level": 9,
                "scale": 1154287.49441732,
                "resolution": 0.00274658203125
              }, {
                "level": 10,
                "scale": 577143.747208662,
                "resolution": 0.001373291015625
              }, {
                "level": 11,
                "scale": 288571.873604331,
                "resolution":  0.0006866455078125
              }, {
                "level": 12,
                "scale": 144285.936802165,
                "resolution": 0.00034332275390625
              }, {
                "level": 13,
                "scale": 72142.9684010827,
                "resolution": 0.000171661376953125
              }, {
                "level": 14,
                "scale": 36071.4842005414,
                "resolution": 8.58306884765629E-05
              }, {
                "level": 15,
                "scale": 18035.7421002707,
                "resolution": 4.29153442382814E-05
              }, {
                "level": 16,
                "scale": 9017.87105013534,
                "resolution": 2.14576721191407E-05
              }, {
                "level": 17,
                "scale": 4508.93552506767,
                "resolution": 1.07288360595703E-05
              }, {
                "level": 18,
                "scale": 2254.467762533835,
                "resolution": 5.36441802978515E-06
              }

              ]

            });
            this.loaded = true;
            this.onLoad(this);
          },

          getTileUrl: function(level, row, col) {
            return this.m_baseURL+ "/wmts?Service=WMTS&Request=GetTile&Version=1.0.0" +  
				"&Style=Default&Format="+this.m_imageFormat+"&serviceMode="+this.m_serviceMode+"&layer="+this.m_layerId +  
				"&TileMatrixSet="+this.m_tileMatrixSetId+"&TileMatrix=" + level + "&TileRow=" + row + "&TileCol=" + col; 
          }
        });
		}

      var map;
	  var m_pointGp;
      
      function init() {
	  initLayer();
	  var vec_basemap = new TianDiTuLayer("http://t0.tianditu.com/img_c/wmts","KVP","tiles","c","img"); 
	  var cva_basemap = new TianDiTuLayer("http://t0.tianditu.com/cia_c/wmts","KVP","tiles","c","cia");
	  m_pointGp = new esri.layers.GraphicsLayer(); 

        //var bnLayer = new esri.layers.FeatureLayer("http://10.33.253.40:8001/ArcGIS/rest/services/Earthquake2/PdaLayer/FeatureServer/0", {outFields: ["*"]});
        //var query = new esri.tasks.Query();
        //query.where = "COUNTY like '%西湖%'";
        var bnSps = new esri.symbol.PictureMarkerSymbol('../images/pinpoint/red.png', 20, 20);
        
        //第一个参数：纬度，第二个参数：经度
        var bnpt = new esri.geometry.Point(<%=request.getAttribute("lon")%>, <%=request.getAttribute("lat") %>, new esri.SpatialReference({ wkid: 4490}));
        
        var bnGraphic = new esri.Graphic(bnpt,bnSps,null,null);
        m_pointGp.add(bnGraphic);
        
      map = new esri.Map("map", {logo : false});
      map.setLevel(8);	        			  				
      map.addLayer(vec_basemap);
	  map.addLayer(cva_basemap);
	  map.addLayer(m_pointGp);
	  //给定一个坐标，使他在浏览器中居中
	  map.centerAt(bnpt);
      }
     dojo.addOnLoad(init);
    </script>
  </head>
  
  <body class="claro">
    <table>
	<tr>
		<td align="left" width="300" height="200">
			<div id="divMap" data-dojo-type="dijit.layout.BorderContainer" 
		         data-dojo-props="design:'headline', gutters:false" 
		         style="width: 100%; height: 100%; margin: 0;">
		
		      <div id="map"
		           data-dojo-type="dijit.layout.ContentPane" 
		           data-dojo-props="region:'center'" > 
		      </div>
		    </div>
		    <!--<div id="divBlock" style="width:300px;height:20px;background-color:red;"></div>-->
		</td>
	</tr>
	<tr>
		<td align="left">
			<font size="5">
				<B>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:property value="tabMapsDetail.time"/>&nbsp;
				<s:property value="tabMapsDetail.address"/>(
					<s:property value="tabMapsDetail.longitude"/>,
					<s:property value="tabMapsDetail.latitude"/>
					)震级：<s:property value="tabMapsDetail.magnitude"/>
					级深度：<s:property value="tabMapsDetail.depth"/>公里  
				</B> 
			</font>
		</td>
	</tr>
	</table>
	
  </body>
  
</html>
