import { useState } from "react";
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import { login } from "./components/authService.jsx";

export default function App() {
    return(
        <>
            <BrowserRouter>
                    <Routes>
                        <Route Component={login} path="/login" />
                    </Routes>
            </BrowserRouter>
        </>
    )
}
