const BASE = "http://localhost:5000/api";

export const api = {
  post: async (path, body) => {
    const res = await fetch(`${BASE}${path}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });
    const data = await res.json();
    if (!res.ok) throw new Error(data.error || "Request failed");
    return data;
  },
  get: async (path) => {
    const res = await fetch(`${BASE}${path}`);
    const data = await res.json();
    if (!res.ok) throw new Error(data.error || "Request failed");
    return data;
  },
};
