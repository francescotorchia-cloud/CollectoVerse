import { useState } from 'react';



export const Register= () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

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
                // Qui potresti reindirizzare l'utente alla pagina di login
            } else {
                const errorData = await res.json().catch(() => ({ message: "Errore sconosciuto. Controlla la console del server." }));
                console.error("Errore durante la registrazione:", errorData);
                alert(`Errore durante la registrazione: ${errorData.message || 'Riprova più tardi.'}`);
            }
        } catch (error) {
            console.error("Errore di rete o di connessione:", error);
            alert("Errore di connessione. Assicurati che il server sia in esecuzione.");
        }
    }

    return (
        <main className="container">
            <form className="card" onSubmit={handleSubmit}>
                <h1>Registrati</h1>

                <label>Username</label>
                <input
                    type="username"
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