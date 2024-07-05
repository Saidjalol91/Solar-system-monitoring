import { Box, useTheme } from "@mui/material";
import KakaoMap from "../../components/KakaoMap";

import Header from "../../components/Header";
import { tokens } from "../../theme";

const Geography = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

 

  return (
    <Box m="20px">
      <Header title="대한민국" subtitle="지도" />
      <Box display="flex" marginTop="10px">
        {/* <Box
          height="75vh"
          border={`1px solid ${colors.grey[100]}`}
          borderRadius="4px"
          width="30%"
          margin="10px"
        >
          <KakaoMap />
        </Box> */}

        <Box
          width="100%"
          height="75vh"
          border={`1px solid ${colors.grey[100]}`}
          borderRadius="4px"
        >
          <KakaoMap />
        </Box>
      </Box>
    </Box>
  );
};

export default Geography;
