import { BrowserRouter, Routes, Route} from 'react-router-dom';
import { login } from "./components/login.jsx";
import { Register } from "./components/register.jsx";
import home from "./components/home.jsx";

export default function App() {
    return(
        <>
            <BrowserRouter>
                    <Routes>
                        <Route Component={login} path="/login" />
                        <Route Component={Register} path="/register" />
                        <Route Component={home} path="/"/>
                    </Routes>
            </BrowserRouter>
        </>
    )
}
