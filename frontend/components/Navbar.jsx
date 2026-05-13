import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';

function NavProva() {
    return (
        <Navbar expand="xxl" className="bg-body-tertiary" fixedTop style={{backgroundSize: "100", backgroundColor: "#bf5a33"}}>
            <Container>
                <Navbar.Brand as={Link} to="/home">
                    <img
                        alt=""
                        src="/collectoverse_logo.jpeg"
                        width="50"
                        height="50"
                        className="d-inline-block align-top"
                    />{' '}
                    CollectoVerse
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/home">Home</Nav.Link>
                        <Nav.Link as={Link} to="/register">Registrati</Nav.Link>
                        <Nav.Link as={Link} to="/login">Login</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavProva;