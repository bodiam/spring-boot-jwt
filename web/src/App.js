import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Dashboard from './components/Dashboard/Dashboard'
import Login from './components/Login/Login';
import Preferences from './components/Preferences/Preferences';
import useToken from './useToken';

function App() {
    const { token, setToken } = useToken();

    if(!token) {
        return <Login setToken={setToken} />
    }

    return (
        <div className="wrapper">
            <h1>Application</h1>
            <pre>{token.token}</pre>
            <BrowserRouter>
                <Routes>
                    <Route path="/dashboard" element={<Dashboard/>}/>
                    <Route path="/preferences" element={<Preferences/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
