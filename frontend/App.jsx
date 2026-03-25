import { useState } from "react";
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import { login } from "./components/login.jsx";
import { Register } from "./components/register.jsx";

export default function App() {
    return(
        <>
            <BrowserRouter>
                    <Routes>
                        <Route Component={login} path="/login" />
                        <Route Component={Register} path="/register" />
                    </Routes>
            </BrowserRouter>
        </>
    )
}
