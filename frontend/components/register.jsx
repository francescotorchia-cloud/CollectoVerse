import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const Register = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const res = await fetch(`http://localhost:8080/users/register`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({username, email, password}),
            });

            if (res.ok) {
                setIsSuccess(true);
                setError(''); // Pulisce eventuali errori precedenti
            } else {
                const errorMessage = await res.text(); // Legge la stringa pulita dal backend
                setError(errorMessage);
            }
        } catch (error) {
            setError("Errore di connessione al server.");
        }
    }

    // Pagina di intermezzo mostrata se isSuccess è true
    if (isSuccess) {
        return (
            <main className="container">
                <div className="card">
                    <h1>Registrazione avvenuta con successo!</h1>
                    <p>Il tuo account è stato creato correttamente.</p>
                    <button onClick={() => navigate('/login')}>Vai al Login</button>
                </div>
            </main>
        );
    }

    // Form di registrazione
    return (
        <main className="container">
            <form className="card" onSubmit={handleSubmit}>
                <h1>Registrati</h1>

                <label>Username</label>
                <input
                    type="text"
                    value={username}
                    onChange={(event) => setUsername(event.target.value)}
                    required
                />
                <label>Email</label>
                <input
                    type="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    required
                />
                <label>Password</label>
                <input
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    required
                />
                {error && <p style={{ color: 'red', fontWeight: 'bold', margin: '10px 0' }}>{error}</p>}
                <button type="submit">Registrati</button>
            </form>
        </main>
    )
}