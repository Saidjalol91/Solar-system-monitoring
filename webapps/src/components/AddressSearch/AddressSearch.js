import axios from "axios";
import React from "react";
import { useState } from "react";
import DaumPostcode from "react-daum-postcode";

const AddressSearch = (props) => {


    const [openPostcode, setOpenPostcode] = useState(false);

    /**
     * handler
     */

    const handleComplete = (data) =>{
        var fulladdress = data.address;
        var roadAddress = data.roadAddress;
        var zipcode = data.zonecode;
        var province = data.sido;
        var city = data.sigungu;
        var bname = data.bname;
        

        console.log(fulladdress);
        console.log(roadAddress);
        // console.log(data);
    }
    const handle = {
        // 버튼 클릭 이벤트
        clickButton: () => {
            setOpenPostcode(current => !current);
        },

        // 주소 선택 이벤트
        selectAddress: (data) => {
            console.log("data malumotlar", data);
            console.log(`
                주소: ${data.address},
                우편번호: ${data.zonecode},
                province:${data.sido},
                city:${data.sigungu},
                bname:${data.bname}
            `)
            setOpenPostcode(false);
        },
    }

    return (
        <div  style={{width:"50%"}}>
            <button onClick={handle.clickButton}>toggle</button>

            {openPostcode && 
                <DaumPostcode 
                    // onComplete={handle.selectAddress}  // 값을 선택할 경우 실행되는 이벤트
                    onComplete = {handleComplete}
                    {...props}
                    autoClose={false} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
                    // defaultQuery='판교역로 235' // 팝업을 열때 기본적으로 입력되는 검색어 
            />}

        </div>
    )
  };
  export default AddressSearch;
  