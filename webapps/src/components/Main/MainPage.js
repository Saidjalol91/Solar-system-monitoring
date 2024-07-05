import React from "react";
import SolarCard from "../Cards/Card";
import Select from "react-select";
import { Button } from "react-bootstrap";
import mainPageStyle from "../Main/MainPage.module.css";
import { calculateNewValue } from "@testing-library/user-event/dist/utils";
import { height } from "@mui/system";

const MainPage = () => {
  const data = [
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 3,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 3,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 3,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 3,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 3,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 2,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    },
    {
      id: 1,
      title: "example title",
      content: "example content",
      image: "https://i.imgur.com/example.jpg",
    }
  ];

  const customStyles = {
    option: (provided, state) => ({
      ...provided,
      color: state.isSelected ? 'white' : 'black',
      padding: 10,
    }),
  };

  const options1 = [
    { value: "0", label: "태양광"},
    { value: "1", label: "태양열" },
    { value: "2", label: "지열" }
  ];
  const options2 = [
    { value: "0", label: "마을태양광"},
    { value: "1", label: "진천유치보수" },
    { value: "2", label: "융복합" }
  ];
  const options3 = [
    { value: "0", label: "일반"},
    { value: "1", label: "공공건물" },
    { value: "2", label: "추택" }
  ];
  const options4 = [
    { value: "0", label: "청주"},
    { value: "1", label: "충주" },
    { value: "2", label: "음성" }
  ];
  return (
    <div style={{ width: "100%", height: "100%", padding: "5px" }}>
      {/* serach Bar */}
      <div 
        style={{
          position: "relative",
          border: "1px solid #ddd",
          backgroundColor: "#fff",
          marginBottom: "10px",
          display: "grid",
          gridTemplateColumns : "22.5% 22.5% 22.5% 22.5% 10%",
        }}
      >
          <div style={{  display: "flex", padding: "10px" }}>
            <label>
              에너지원
            </label>
            <div style={{width:"75%", justifyContent:"center"}}>
              <Select 
                   options={options1}
                   defaultValue={{ label: "태양광", value: 0 }} 
                   styles={customStyles}
                   />
            </div>
          </div>
          <div style={{display: "flex", padding: "10px" }}>
            <label> 프로젝트</label>
            <div style={{width:"75%", justifyContent:"center"}}>
              <Select 
                  options={options2} 
                  defaultValue={{ label: "융복합", value: 0 }} 
                  styles={customStyles}
                  />
            </div>
          </div>
          <div style={{  display: "flex", padding: "10px" }}>
            <label> 수용가 </label>
            <div style={{width:"75%", justifyContent:"center"}}>
              <Select 
                   options={options3} 
                   styles={customStyles} 
                   defaultValue={{ label: "일반", value: 0 }} 
                   />
            </div>
          </div>
          <div style={{  display: "flex", padding: "10px"}}>
            <label>
              지역
            </label>
            <div style={{width:"75%", justifyContent:"center"}}>
              <Select 
                  options={options4} 
                  styles={customStyles} 
                  defaultValue={{ label: "청주", value: 0 }} 
                  />
            </div>
          </div>
          <div style={{  alignContent: "center", display: "flex", paddingTop:"10px", paddingBottom:"10px"}}>
            <Button style={{height:"38px"}}>조회</Button>
          </div>
      </div>
      {/* Main showing card bar */}
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          padding:"0 5px",
          width: "100%",
          border: "1px solid grey",
          overflowY: "scroll",
          maxHeight:"680px",
          borderRadius:"10px"
        }}
      >
        {data.map((panel) => (
          <SolarCard key={panel.id} panel={panel} />
        ))}
      </div>
    </div>
  );
};
export default MainPage;
