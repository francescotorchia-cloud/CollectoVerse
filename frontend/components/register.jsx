import { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Assuming you are using react-router-dom

export const Register = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError(''); // Reset error message on new submission

        try {
            const res = await fetch(`http://localhost:8080/users/register`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({username, email, password}),
            });

            if (res.ok) {
                const data = await res.json();
                console.log("Registrazione avvenuta con successo:", data);
                alert("Registrazione avvenuta con successo!");
                navigate('/login'); // Redirect to login page
            } else {
                const errorData = await res.json().catch(() => ({ message: "An unknown error occurred. Please check the server console." }));
                console.error("Errore durante la registrazione:", errorData);
                setError(errorData.message || 'Registration failed. Please try again later.');
            }
        } catch (error) {
            console.error("Network or connection error:", error);
            setError("Connection error. Please ensure the server is running.");
        }
    }

    return (
        <main className="container">
            <form className="card" onSubmit={handleSubmit}>
                <h1>Registrati</h1>

                {error && <p className="error">{error}</p>}

                <label htmlFor="username">Username</label>
                <input
                    id="username"
                    type="text" // Corrected from type="username"
                    value={username}
                    onChange={(event) => setUsername(event.target.value)}
                    required
                />

                <label htmlFor="email">Email</label>
                <input
                    id="email"
                    type="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    required
                />

                <label htmlFor="password">Password</label>
                <input
                    id="password"
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    required
                />
                <button type="submit">Registrati</button>
            </form>
        </main>
    )
}