import { useState } from "react";
import { login } from "./services/authService";

export default function App() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [user, setUser] = useState(null);

    async function handleSubmit(e) {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
            const loggedUser = await login(email, password);
            setUser(loggedUser);
            localStorage.setItem("user", JSON.stringify(loggedUser));
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    if (user) {
        return (
            <main className="container">
                <h1>Benvenuto, {user.username}</h1>
                <p>Login effettuato con {user.email}</p>
            </main>
        );
    }

    return (
        <main className="container">
            <form className="card" onSubmit={handleSubmit}>
                <h1>Login</h1>

                <label>Email</label>
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />

                <label>Password</label>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />

                {error && <p className="error">{error}</p>}

                <button type="submit" disabled={loading}>
                    {loading ? "Accesso..." : "Accedi"}
                </button>
            </form>
        </main>
    );
}