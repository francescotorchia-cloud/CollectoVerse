import { useState } from "react";

export const login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [user, setUser] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError("");
        setLoading(true);

        try {
            const res = await fetch(`http://localhost:8080/users/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });

            if (!res.ok) {
                const message = await res.text();
                throw new Error(message || "Credenziali non valide");
            }

            const loggedUser = await res.json();
            setUser(loggedUser);
            localStorage.setItem("user", JSON.stringify(loggedUser));
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

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

                {error && <p className="error">{error}</p>}

                <button type="submit" disabled={loading}>
                    {loading ? "Accesso..." : "Accedi"}
                </button>
            </form>
        </main>
    );
};
