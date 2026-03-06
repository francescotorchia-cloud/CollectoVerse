

export async function login(email, password) {
    const res = await fetch(`http://localhost:8080/users/login`, {
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