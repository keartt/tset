<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Map</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@latest/en/v6.5.0/css/ol.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #map { width: 100%; height: 500px; }
        #addLayerBtn { margin-top: 10px; }
    </style>
</head>
<body>
<h2>Map with Points</h2>
<div id="map"></div>
<button id="addLayerBtn">지오서버 레이어 추가</button>

<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@latest/en/v6.5.0/build/ol.js"></script>
<script>
    const map = new ol.Map({
        target: 'map',
        layers: [
            new ol.layer.Tile({ source: new ol.source.OSM() })
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat([126.9780, 37.5665]),
            zoom: 10
        })
    });

    const vectorSource = new ol.source.Vector();
    const vectorLayer = new ol.layer.Vector({
        source: vectorSource,
        style: new ol.style.Style({
            image: new ol.style.Circle({
                radius: 6,
                fill: new ol.style.Fill({ color: 'red' }),
                stroke: new ol.style.Stroke({ color: 'black', width: 1 })
            })
        })
    });
    map.addLayer(vectorLayer);

    var proxy = document.location.origin+ '/proxy.do?url='
    document.getElementById('addLayerBtn').addEventListener('click', function() {
        const wmsLayer = new ol.layer.Tile({
            source: new ol.source.TileWMS({
                url: proxy +'http://localhost:8080/geoserver/wms',
                params: {
                    'LAYERS': 'test:test',
                    'TILED': true,
                    'SRS': 'EPSG:5187', // 레이어의 좌표계 설정
                    'FORMAT': 'image/png' // 이미지 포맷 설정
                },
                serverType: 'geoserver',
                crossOrigin: 'anonymous'
            })
        });
        map.addLayer(wmsLayer);
    });
</script>
</body>
</html>

