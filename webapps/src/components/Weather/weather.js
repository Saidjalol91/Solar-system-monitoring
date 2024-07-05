import React, { useState, useEffect } from "react";

const Weather = () => {
  const [lat, setLat] = useState([]);
  const [long, setLong] = useState([]);
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      navigator.geolocation.getCurrentPosition(function (position) {
        setLat(position.coords.latitude);
        setLong(position.coords.longitude);
      });

      await fetch(
        `https://api.openweathermap.org/data/2.5/weather?lat=36.8805353485744&lon=127.30364722597076&apikey=f8c146233a65c8f1a4e2229f705f4c88&units=metric`
      )
        .then((res) => res.json())
        .then((result) => {
          setData(result);
          console.log(result);
        });
    };

    console.log("LAttatude is : ", lat);
    console.log("Longitude is : ", long);

    fetchData();
  }, [lat, long]);

  return (
    <>
      {typeof data.main != "undefined" ? (
        <div style={{ "margin-right":"155px" }}>
          <p style={{ "margin-top": "5px", "margin-bottom": "5px" }}>
            날짜 : {new Date().toLocaleString() + ""}
          </p>
          <p style={{ "margin-top": "5px", "margin-bottom": "5px" }}>
            일출 :{" "}
            {new Date(data.sys.sunrise * 1000).toLocaleTimeString("en-IN")}
          </p>
          <p style={{ "margin-top": "5px", "margin-bottom": "5px" }}>
            일몰 :{" "}
            {new Date(data.sys.sunset * 1000).toLocaleTimeString("en-IN")}
          </p>
          <p style={{ "margin-top": "5px", "margin-bottom": "5px" }}>
            강수 : {data.main.humidity} %
          </p>
          <p style={{ "margin-top": "5px", "margin-bottom": "5px" }}>
            날씨 : {data.main.temp} &deg;C
          </p>
        </div>
      ) : (
        <div>
          <p> Loading.....</p>
        </div>
      )}
    </>
  );
};
export default Weather;
