import React, { useState, useEffect } from "react";
import { positionMap } from "../data/position";
import {
  Map,
  MapMarker,
  MapTypeControl,
  ZoomControl,
  CustomOverlayMap,
  MarkerClusterer,
} from "react-kakao-maps-sdk";

const KakaoMap = ({ isDashboard = false }) => {
  // const { kakao } = window;

  const markerImageSrc =  "../../assets/user.png";
  const imageSize = { width: 50, height: 50 };
  const spriteSize = { width: 36, height: 98 };

  const [positions, setPositions] = useState([]);

  useEffect(() => {
    setPositions(positionMap);
  }, []);

  //console.log("positions: " + JSON.stringify(positions.lat));
  console.log("positions: " + positions);

  const Content = () => (
    <div className="overlay_info">
      <a
        href="https://place.map.kakao.com/17600274"
        target="_blank"
        rel="noreferrer"
      >
        <strong>월정리 해수욕장</strong>
      </a>
      <div className="desc">
        <img
          src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_thumb.png"
          alt=""
        />
        <span className="address">제주특별자치도 제주시 구좌읍 월정리 33-3</span>
      </div>
    </div>
  )


  return (
    <Map
      center={{ lat: 36.2683, lng: 127.6358 }}
      style={{ width: "100%", height: "100%" }}
      level={14}
    >


      

      <MarkerClusterer
        averageCenter={true} // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel={10} // 클러스터 할 최소 지도 레벨
      >
        {positions.map((pos) => (
          <MapMarker
            key={`${pos.lat}-${pos.lng}`}
            position={{
              lat: pos.lat,
              lng: pos.lng,
            }}
            image ={{
              src: markerImageSrc,
              size: imageSize,
              options: {
                
              }
            }}
          />
        ))}
      </MarkerClusterer>
      <CustomOverlayMap
          position={{
            lat: 33.55635,
            lng: 126.795841,
          }}
          xAnchor={0.5}
          yAnchor={1.1}
        >
          <Content />
      </CustomOverlayMap>

      {/* <MapTypeControl position={kakao.maps.ControlPosition.TOP} /> */}
      {/* <ZoomControl position={"BOTTOMRIGHT"} /> */}
    </Map>
  );
};

export default KakaoMap;
