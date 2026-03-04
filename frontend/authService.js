const API_BASE_URL = "http://localhost:8080";

export async function login(email, password) {
    const res = await fetch(`${API_BASE_URL}/users/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
        let message = "Errore login";
        try {
            message = await res.text();
        } catch (_) {}
        throw new Error(message || "Credenziali non valide");
    }

    return res.json();
}