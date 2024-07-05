import { Box } from "@mui/material";
import Header from "../../components/Header";
import MainPage from "../../components/Main/MainPage";


const Main = () => {
  return (
    <Box m="20px">
      <Header title="Main" subtitle="Main Query Page" />
      <Box height="80vh">
         <MainPage/>
      </Box>
    </Box>
  );
};

export default Main;