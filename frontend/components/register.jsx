import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const Register = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);

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
                // Imposta lo stato a true per mostrare la pagina di intermezzo
                setIsSuccess(true);
            } else {
                const errorData = await res.json().catch(() => ({ message: "Errore sconosciuto. Controlla la console del server." }));
                alert(`Errore durante la registrazione: ${errorData.message || 'Riprova più tardi.'}`);
            }
        } catch (error) {
            alert("Errore di connessione. Assicurati che il server sia in esecuzione.");
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
                <button type="submit">Registrati</button>
            </form>
        </main>
    )
}