import React from "react";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";
import "./popupcss.css";
import { useState } from "react";

const PopupComp = ({ onFormSubmit, onInputChange, onClose }) => {
  const handleSubmit = (e) => {
    e.preventDefault();
    onFormSubmit();
    onClose();
  
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    onInputChange(name, value);
  };

  return (
    <div>
      <Popup
        contentStyle={{
          width: "700px",
          height: "500px",
          background: "white",
          color: "black",
          fontSize: "16px",
          fontWeight: "bold",
        }}
        trigger={<button> 비밀번호병경 </button>}
        modal
        nested
      >
        {(close) => (
          <div className="modal">
            <div className="content" style={{ padding: "25px" }}>
              <form
                onSubmit={handleSubmit}
                id="passwordForm"
                style={{ position: "relative" }}
              >
                <i></i>
                <em>비밀번호 변경</em>
                <div className="change-info">
                  <ul>
                    <li>
                      <span>새 비밀번호를 입력하세요.</span>
                    </li>
                    <li>
                      <span>기존과 동일한 비밀번호를 사용을 삼가해주세요.</span>
                    </li>
                  </ul>
                </div>
                <table>
                  <colgroup>
                    <col style={{ width: "25%" }} />
                    <col style={{ width: "75%" }} />
                  </colgroup>
                  <tbody>
                    <tr>
                      <th>비밀번호</th>
                      <td>
                        <input
                          className="input_style"
                          name="password"
                          type="password"
                          placeholder="6~12 자 사이의 영문소, 숫자, 특수문자를 입력하세요."
                          onChange={handleInputChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <th>비밀번호 확인</th>
                      <td>
                        <input
                          className="input_style"
                          type="password"
                          placeholder="비밀번호를 재입력하세요."
                          name="password_retype"
                          onChange={handleInputChange}
                        />
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div className="form-btn">
                  <button type="submit">변경완료</button>
                  <button
                    onClick={() => close()}
                    style={{ backgroundColor: "#666", marginLeft: "10px" }}
                  >
                    닫기
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}
      </Popup>
    </div>
  );
};

export default PopupComp;
