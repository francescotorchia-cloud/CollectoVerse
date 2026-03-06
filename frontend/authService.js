const API_BASE_URL = "/api";

export async function login(email, password) {
    const res = await fetch(`${API_BASE_URL}/users/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
        const message = await res.text();
        throw new Error(message || "Credenziali non valide");
    }

    return res.json();
}