import { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import Topbar from "./scenes/global/Topbar";
import Sidebar from "./scenes/global/Sidebar";
import Dashboard from "./scenes/dashboard";
import Team from "./scenes/team";
import Invoices from "./scenes/invoices";
import Contacts from "./scenes/contacts";
import Bar from "./scenes/bar";
import Line from "./scenes/line";
import Form from "./scenes/form";
import Main from "./scenes/main";
import Pie from "./scenes/pie";
import FAQ from "./scenes/faq";
import Geography from "./scenes/geography";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { ColorModeContext, useMode } from "./theme";
import Calendar from "./scenes/calendar/calendar";
import Login from "./components/Login/Login";
import FindUser from "./components/FindUser/FindUser";
import Register from "./components/Register/Register";
import UserDetailForm from "./scenes/userDetailForm";

function App() {
  const [theme, colorMode] = useMode();
  const [isSidebar, setIsSidebar] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const handleLogin = (value) => {
    setIsAuthenticated(true);
  };

  useEffect(() => {
    const navigationEntries = window.performance.getEntriesByType('navigation');
    if (navigationEntries.length > 0 && navigationEntries[0].type === 'reload') {
      setIsAuthenticated(true);
    }
  }, []);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        {isAuthenticated ? (
          <div className="app">
            <Sidebar isSidebar={isSidebar} />
            <main className="content">
              <Topbar setIsSidebar={setIsSidebar} />
              <Routes>
                <Route path="/dash" element={<Dashboard />} />
                <Route path ="/main" element={<Main/>}/>
                <Route path="/team" element={<Team />} />
                <Route path="/contacts" element={<Contacts />} />
                <Route path="/invoices" element={<Invoices />} />
                <Route path="/form" element={<Form />} />
                <Route path="/user" element={<UserDetailForm />} />
                {/* <Route path="/form" element={<UserDetailForm />} /> */}
                <Route path="/bar" element={<Bar />} />
                <Route path="/pie" element={<Pie />} />
                <Route path="/line" element={<Line />} />
                <Route path="/faq" element={<FAQ />} />
                {/* <Route path="/calendar" element={<Calendar />} /> */}
                <Route path="/geography" element={<Geography />} />
              </Routes>
            </main>
          </div>
        ) : (
          <>
             <Routes>
                  <Route path ="/" element={<Login onLogin={handleLogin} />}/>
                  <Route path="/login" element={<Login  onLogin={handleLogin} />}/>
                  <Route path="/signup" element={<Register />} />
                  <Route path="/findpass" element={<FindUser />}/>
             </Routes>
          </>
        )}
      </ThemeProvider>
    </ColorModeContext.Provider>
  );

 
}

export default App;
