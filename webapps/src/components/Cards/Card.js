import React, {useState} from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import { CardActions } from "@mui/material";
import { Button } from "react-bootstrap";
import { Tooltip } from 'react-tooltip'
import { height } from "@mui/system";
import { BorderColor } from "@mui/icons-material";
import zIndex from "@mui/material/styles/zIndex";

const SolarCard = ({ panel }) => {
  
  

 const [isActive, setIsActive] = useState(false);
 const [showToolTip, setShowToolTip] = useState(false);

 const solarDetailHandler = ()=> {
  setIsActive(!isActive);
}


  const userDetailsHandler = () =>{
    alert("this is second user popup0");
  }

  const handleMouseEnter =() =>{
    setShowToolTip(true);
  }

  const handleMouseLeave =() =>{
    setShowToolTip(false);
  }


  const solarImage =  "../../assets/solarimage.png";
  const homeImage = "../../assets/home.png";
  const officeImage = "../../assets/office.png";

  const Tooltip = () =>{
    return(
         <div
            style={
              {
                position:"fixed",
                backgroundColor:"silver",
                border:"2px solid orange",
                marginTop:"80px",
                marginLeft:"120px",
                color:"black",
                fontWeight:"bold",
                padding:"5px 10px",
                borderRadius:"5px",
                fontSize:"12px",
                opacity:"0.9",
                zIndex:"1000",
                height:"80px",
                width:"190px"
              }
            }
         >
             <div style={{position:"absolute", left:"5px"}}>
                 <span style={{display:"block", float:"left", margin:"5px 0"}}>모듈 : (주)태웅이엔에스 0314566</span>
                 <span style={{display:"block", float:"left", margin:"5px 0"}}>인벝터 : 다씉테크 04355556</span>
                 <span style={{display:"block", float:"left", margin:"5px 0"}}>시공사:태웅이엔에스(0432619000)</span>
             </div>
         </div>
    );
  }

  return( <div
    style={{
      display: "flex",
      flexWrap: "wrap",
      margin: "5px",
      borderRadius: "30px",
    }}
  >
    <Card
      sx={{ minWidth: 250, minHeight: 200, backgroundColor: isActive ? "red" : "#fca" }}
      variant="outlined"
    >
      <CardActions style={{marginTop:"20px"}}>
        
        <Button
          size="small"
          style={{
            width: "225px",
            display: "flex",
            padding: "20px 10px",
            backgroundColor:"white",
            color: "black",
            border: "solid 0.5px grey",
            cursor: "pointer"
          }}
          onClick ={solarDetailHandler}
          onMouseEnter={handleMouseEnter}
          onMouseLeave={handleMouseLeave}
        >
          <img src={solarImage} style={{width:"45px", height:"45px"}}/>
          태양광
          <label style={{fontSize:"12px", paddingTop:"0", paddingLeft:"10px", width:"40%" }}>23kw / 100kw</label>
          {showToolTip && <Tooltip/>}
        </Button>
        
      </CardActions>
      <CardActions>
        <Button
          size="small"
          style={{
            width: "225px",
            padding: "20px 10px",
            backgroundColor: "white",
            color: "black",
            border :"solid 0.5px grey"
          }}
          onClick = {userDetailsHandler}
        >
          <img src={officeImage} style={{margin:"0 10px", width:"40px", height:"40px"}}/>
           테웅이엔에스
        </Button>
      </CardActions>
    </Card>
  </div>);
};

export default SolarCard;
