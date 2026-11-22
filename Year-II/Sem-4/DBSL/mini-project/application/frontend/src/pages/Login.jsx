import { useState } from "react";
import { api } from "../api";

const ORANGE = "#E87722";

export default function Login({ onLogin }) {
  const [role, setRole]         = useState("student");
  const [email, setEmail]       = useState("");
  const [password, setPassword] = useState("");
  const [error, setError]       = useState("");
  const [loading, setLoading]   = useState(false);

  const handleLogin = async () => {
    setError(""); setLoading(true);
    try {
      const { user } = await api.post("/login", { email, password, role });
      onLogin(user);
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  };

  const demoFill = (r) => {
    setRole(r);
    setEmail(r === "student" ? "shlok@cms.edu" : "ramesh@cms.edu");
    setPassword("pass123");
  };

  return (
    <div style={{
      minHeight: "100vh", background: "#f0f0f0",
      fontFamily: "'Segoe UI', Arial, sans-serif",
      display: "flex", flexDirection: "column",
    }}>
      {/* Top white bar — mirrors dashboard TopNav */}
      <div style={{
        background: "#fff", borderBottom: "1px solid #ddd",
        display: "flex", alignItems: "center",
        padding: "0 32px", height: "68px",
        boxShadow: "0 1px 4px rgba(0,0,0,0.08)"
      }}>
        <div style={{ display: "flex", alignItems: "center", gap: "16px" }}>
          <div style={{
            width: 44, height: 44, borderRadius: "50%", background: "#8B1A1A",
            display: "flex", alignItems: "center", justifyContent: "center"
          }}>
            <span style={{ color: "#fff", fontSize: 15, fontWeight: 700 }}>M</span>
          </div>
          <div style={{ width: 1, height: 36, background: "#ddd" }} />
          <span style={{ fontSize: 20, fontWeight: 600, color: "#222" }}>MIT Manipal</span>
        </div>
      </div>

      {/* Orange accent bar — mirrors dashboard OrangeNav */}
      <div style={{ background: ORANGE, height: "48px" }} />

      {/* Page body */}
      <div style={{ flex: 1, display: "flex", alignItems: "center", justifyContent: "center", padding: "48px 16px" }}>
        <div style={{ width: "100%", maxWidth: 460 }}>

          {/* Header */}
          <div style={{ textAlign: "center", marginBottom: 32 }}>
            <div style={{
              width: 64, height: 64, borderRadius: 14, background: ORANGE,
              display: "flex", alignItems: "center", justifyContent: "center",
              margin: "0 auto 16px", boxShadow: "0 4px 16px rgba(232,119,34,0.3)"
            }}>
              <svg width="32" height="32" fill="none" stroke="#fff" strokeWidth="2" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round"
                  d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253" />
              </svg>
            </div>
            <h1 style={{ fontSize: 28, fontWeight: 700, color: "#1a1a1a", margin: 0 }}>CourseMS</h1>
            <p style={{ color: "#777", fontSize: 15, marginTop: 6 }}>Course Management System · MAHE</p>
          </div>

          {/* Card */}
          <div style={{
            background: "#fff", border: "1px solid #ddd", borderRadius: 4,
            padding: "32px 32px 28px", boxShadow: "0 2px 8px rgba(0,0,0,0.07)"
          }}>
            {/* Role toggle */}
            <div style={{ display: "flex", background: "#f0f0f0", borderRadius: 4, padding: 3, marginBottom: 28 }}>
              {["student", "instructor"].map(r => (
                <button key={r} onClick={() => setRole(r)} style={{
                  flex: 1, padding: "9px 0", borderRadius: 3,
                  fontSize: 14, fontWeight: 600, border: "none", cursor: "pointer",
                  textTransform: "capitalize", transition: "all 0.15s",
                  background: role === r ? ORANGE : "transparent",
                  color: role === r ? "#fff" : "#666",
                  boxShadow: role === r ? "0 1px 4px rgba(232,119,34,0.35)" : "none",
                }}>
                  {r}
                </button>
              ))}
            </div>

            {/* Fields */}
            <div style={{ display: "flex", flexDirection: "column", gap: 18 }}>
              <div>
                <label style={{
                  display: "block", fontSize: 12, fontWeight: 600, color: "#555",
                  marginBottom: 7, textTransform: "uppercase", letterSpacing: "0.07em"
                }}>Email</label>
                <input type="email" value={email} onChange={e => setEmail(e.target.value)}
                  placeholder={role === "student" ? "shlok@cms.edu" : "ramesh@cms.edu"}
                  style={{
                    width: "100%", border: "1px solid #ccc", background: "#fafafa",
                    color: "#1a1a1a", borderRadius: 3, padding: "11px 14px", fontSize: 14,
                    outline: "none", boxSizing: "border-box", transition: "border-color 0.15s",
                  }}
                  onFocus={e => e.target.style.borderColor = ORANGE}
                  onBlur={e => e.target.style.borderColor = "#ccc"}
                />
              </div>
              <div>
                <label style={{
                  display: "block", fontSize: 12, fontWeight: 600, color: "#555",
                  marginBottom: 7, textTransform: "uppercase", letterSpacing: "0.07em"
                }}>Password</label>
                <input type="password" value={password} onChange={e => setPassword(e.target.value)}
                  placeholder="••••••••"
                  onKeyDown={e => e.key === "Enter" && handleLogin()}
                  style={{
                    width: "100%", border: "1px solid #ccc", background: "#fafafa",
                    color: "#1a1a1a", borderRadius: 3, padding: "11px 14px", fontSize: 14,
                    outline: "none", boxSizing: "border-box", transition: "border-color 0.15s",
                  }}
                  onFocus={e => e.target.style.borderColor = ORANGE}
                  onBlur={e => e.target.style.borderColor = "#ccc"}
                />
              </div>
            </div>

            {error && (
              <div style={{
                marginTop: 16, background: "#ffebee", border: "1px solid #ffcdd2",
                color: "#c62828", fontSize: 13, borderRadius: 3, padding: "10px 14px"
              }}>
                {error}
              </div>
            )}

            <button onClick={handleLogin} disabled={loading} style={{
              marginTop: 24, width: "100%", background: loading ? "#cf6510" : ORANGE,
              color: "#fff", fontWeight: 700, fontSize: 15, padding: "13px 0",
              borderRadius: 3, border: "none", cursor: loading ? "not-allowed" : "pointer",
              transition: "background 0.15s",
            }}
              onMouseOver={e => { if (!loading) e.target.style.background = "#cf6510"; }}
              onMouseOut={e => { if (!loading) e.target.style.background = ORANGE; }}>
              {loading ? "Signing in…" : "Sign In"}
            </button>

            {/* Demo shortcuts */}
            <div style={{ marginTop: 24, paddingTop: 20, borderTop: "1px solid #eee" }}>
              <p style={{ textAlign: "center", fontSize: 12, color: "#999", marginBottom: 12 }}>Demo quick-fill</p>
              <div style={{ display: "flex", gap: 10 }}>
                <button onClick={() => demoFill("student")} style={{
                  flex: 1, fontSize: 13, color: "#666", background: "#fff",
                  border: "1px solid #ddd", borderRadius: 3, padding: "8px 0",
                  cursor: "pointer", transition: "all 0.15s",
                }}
                  onMouseOver={e => { e.currentTarget.style.borderColor = ORANGE; e.currentTarget.style.color = ORANGE; }}
                  onMouseOut={e => { e.currentTarget.style.borderColor = "#ddd"; e.currentTarget.style.color = "#666"; }}>
                  Student Demo
                </button>
                <button onClick={() => demoFill("instructor")} style={{
                  flex: 1, fontSize: 13, color: "#666", background: "#fff",
                  border: "1px solid #ddd", borderRadius: 3, padding: "8px 0",
                  cursor: "pointer", transition: "all 0.15s",
                }}
                  onMouseOver={e => { e.currentTarget.style.borderColor = "#1A6B3C"; e.currentTarget.style.color = "#1A6B3C"; }}
                  onMouseOut={e => { e.currentTarget.style.borderColor = "#ddd"; e.currentTarget.style.color = "#666"; }}>
                  Instructor Demo
                </button>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
}
