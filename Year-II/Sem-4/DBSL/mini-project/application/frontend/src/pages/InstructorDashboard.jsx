import { useState, useEffect } from "react";
import { api } from "../api";

const THUMBS = [
  "https://images.unsplash.com/photo-1518770660439-4636190af475?w=600&q=80",
  "https://images.unsplash.com/photo-1501854140801-50d01698950b?w=600&q=80",
  "https://images.unsplash.com/photo-1454496522488-7a8e488e8606?w=600&q=80",
  "https://images.unsplash.com/photo-1477346611705-65d1883cee1e?w=600&q=80",
  "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=600&q=80",
  "https://images.unsplash.com/photo-1581092918056-0c4c3acd3789?w=600&q=80",
  "https://images.unsplash.com/photo-1509228468518-180dd4864904?w=600&q=80",
];

const ACCENT = "#1A6B3C";

export default function InstructorDashboard({ user, onLogout }) {
  const [page, setPage] = useState("courses");

  return (
    <div style={{ fontFamily: "'Segoe UI', Arial, sans-serif", background: "#f0f0f0", minHeight: "100vh" }}>
      <TopNav user={user} onLogout={onLogout} />
      <GreenNav page={page} setPage={setPage} />
      {page === "courses"     && <CoursesPage     user={user} />}
      {page === "assignments" && <AssignmentsPage user={user} />}
      {page === "grade"       && <GradePage       user={user} />}
    </div>
  );
}

function TopNav({ user, onLogout }) {
  return (
    <div style={{
      background: "#fff", borderBottom: "1px solid #ddd",
      display: "flex", alignItems: "center", justifyContent: "space-between",
      padding: "0 32px", height: "68px", position: "sticky", top: 0, zIndex: 100,
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
        <span style={{
          marginLeft: 6, fontSize: 12, background: "#e8f5e9", color: ACCENT,
          padding: "3px 10px", borderRadius: 3, fontWeight: 600
        }}>INSTRUCTOR</span>
      </div>
      <div style={{ display: "flex", alignItems: "center", gap: "22px" }}>
        <div style={{
          width: 36, height: 36, borderRadius: "4px", background: ACCENT,
          display: "flex", alignItems: "center", justifyContent: "center",
          color: "#fff", fontWeight: 700, fontSize: 14
        }}>
          {user.name.slice(0, 2).toUpperCase()}
        </div>
        <span style={{ fontSize: 14, fontWeight: 600, color: "#333" }}>
          {user.name.toUpperCase()}
        </span>
        <button onClick={onLogout} style={{
          fontSize: 13, color: "#888", background: "none", border: "1px solid #ddd",
          cursor: "pointer", padding: "5px 14px", borderRadius: 4
        }}>Sign out</button>
      </div>
    </div>
  );
}

function GreenNav({ page, setPage }) {
  // Only functional pages — no dummy links
  const links = [
    { id: "courses",     label: "My Courses" },
    { id: "assignments", label: "Assignments" },
    { id: "grade",       label: "Grade Students" },
  ];
  return (
    <div style={{ background: ACCENT, padding: "0 32px", display: "flex" }}>
      {links.map(l => (
        <button key={l.id}
          onClick={() => setPage(l.id)}
          style={{
            background: "none", border: "none", cursor: "pointer", color: "#fff",
            fontSize: 15, fontWeight: 400, padding: "14px 20px",
            borderBottom: page === l.id ? "3px solid #fff" : "3px solid transparent",
            transition: "border-color 0.15s",
          }}
          onMouseOver={e => e.currentTarget.style.background = "rgba(0,0,0,0.1)"}
          onMouseOut={e => e.currentTarget.style.background = "none"}>
          {l.label}
        </button>
      ))}
    </div>
  );
}

function CoursesPage({ user }) {
  const [courses, setCourses]   = useState([]);
  const [loading, setLoading]   = useState(true);
  const [form, setForm]         = useState({ course_name: "", credits: "" });
  const [msg, setMsg]           = useState(null);
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    api.get(`/courses/instructor/${user.id}`).then(setCourses).finally(() => setLoading(false));
  }, [user.id]);

  const create = async () => {
    if (!form.course_name || !form.credits) return;
    try {
      const c = await api.post("/courses", { ...form, credits: Number(form.credits), instructor_id: user.id });
      setCourses(prev => [...prev, c]);
      setForm({ course_name: "", credits: "" });
      setMsg({ type: "ok", text: "Course created!" });
      setShowForm(false);
    } catch (e) {
      setMsg({ type: "err", text: e.message });
    }
  };

  return (
    <div style={{ maxWidth: 1280, margin: "0 auto", padding: "0 32px 48px" }}>
      {/* Hero */}
      <div style={{ position: "relative", height: 200, marginBottom: 32, overflow: "hidden", borderRadius: "0 0 4px 4px" }}>
        <img src="https://images.unsplash.com/photo-1581092918056-0c4c3acd3789?w=1400&q=80" alt="MIT"
          style={{ width: "100%", height: "100%", objectFit: "cover", objectPosition: "center 40%" }} />
        <div style={{ position: "absolute", inset: 0, background: "linear-gradient(to right, rgba(0,60,20,0.65), rgba(0,0,0,0.1))" }} />
        <div style={{ position: "absolute", bottom: 28, left: 32, color: "#fff", fontSize: 32, fontWeight: 400 }}>
          Manipal Institute of Technology
        </div>
      </div>

      {/* Create button */}
      <div style={{ marginBottom: 20, display: "flex", justifyContent: "flex-end" }}>
        <button onClick={() => setShowForm(f => !f)} style={{
          background: ACCENT, color: "#fff", border: "none", borderRadius: 3,
          padding: "9px 22px", fontSize: 14, fontWeight: 600, cursor: "pointer"
        }}>+ Create Course</button>
      </div>

      {/* Create form */}
      {showForm && (
        <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px", marginBottom: 20 }}>
          <h3 style={{ margin: "0 0 16px", fontSize: 16, fontWeight: 600, color: "#222" }}>New Course</h3>
          <div style={{ display: "flex", gap: 12, flexWrap: "wrap" }}>
            <input value={form.course_name} onChange={e => setForm(f => ({ ...f, course_name: e.target.value }))}
              placeholder="Course name"
              style={{ flex: 1, minWidth: 200, border: "1px solid #ccc", borderRadius: 3, padding: "9px 14px", fontSize: 14 }} />
            <input value={form.credits} onChange={e => setForm(f => ({ ...f, credits: e.target.value }))}
              type="number" min="1" placeholder="Credits"
              style={{ width: 110, border: "1px solid #ccc", borderRadius: 3, padding: "9px 14px", fontSize: 14 }} />
            <button onClick={create} style={{
              background: ACCENT, color: "#fff", border: "none", borderRadius: 3,
              padding: "9px 22px", fontSize: 14, fontWeight: 600, cursor: "pointer"
            }}>Save</button>
          </div>
          {msg && <div style={{ fontSize: 13, color: msg.type === "ok" ? "#2e7d32" : "#c62828", marginTop: 10 }}>{msg.text}</div>}
        </div>
      )}

      {/* Courses grid */}
      <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px 24px 36px" }}>
        <div style={{ display: "flex", alignItems: "center", gap: 8, marginBottom: 24 }}>
          <span style={{ fontSize: 18, fontWeight: 600, color: "#222" }}>My Courses</span>
          <span style={{ fontSize: 15, color: "#888" }}>▾</span>
        </div>
        {loading ? <Spinner /> : (
          <div style={{ display: "grid", gridTemplateColumns: "repeat(4, 1fr)", gap: "20px" }}>
            {courses.length === 0 && (
              <div style={{ gridColumn: "1/-1", textAlign: "center", color: "#999", padding: "48px 0", fontSize: 15 }}>
                No courses yet. Create your first course above.
              </div>
            )}
            {courses.map((c, idx) => (
              <div key={c.course_id} style={{
                background: "#fff", border: "1px solid #ddd", borderRadius: 2,
                overflow: "hidden", boxShadow: "0 1px 3px rgba(0,0,0,0.06)",
                transition: "box-shadow 0.2s", cursor: "pointer",
              }}
                onMouseOver={e => e.currentTarget.style.boxShadow = "0 4px 14px rgba(0,0,0,0.15)"}
                onMouseOut={e => e.currentTarget.style.boxShadow = "0 1px 3px rgba(0,0,0,0.06)"}>
                <div style={{ height: 155, overflow: "hidden" }}>
                  <img src={THUMBS[idx % THUMBS.length]} alt={c.course_name}
                    style={{ width: "100%", height: "100%", objectFit: "cover" }} />
                </div>
                <div style={{ padding: "14px 16px 16px" }}>
                  <div style={{ fontSize: 14, fontWeight: 700, color: "#1a1a1a", lineHeight: 1.4, marginBottom: 7 }}>
                    {c.course_name}
                  </div>
                  <div style={{ fontSize: 12, color: "#666", marginBottom: 5 }}>
                    009_CMS_{c.course_id}_IV_2025-2026
                  </div>
                  <div style={{ display: "flex", alignItems: "center", gap: 5, fontSize: 12, color: "#666" }}>
                    <span style={{ width: 5, height: 5, background: "#666", borderRadius: "50%", flexShrink: 0 }} />
                    AY 2025-2026 | Sem IV | {c.credits} credits
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

function AssignmentsPage({ user }) {
  const [assignments, setAssignments] = useState([]);
  const [courses, setCourses]         = useState([]);
  const [loading, setLoading]         = useState(true);
  const [showForm, setShowForm]       = useState(false);
  const [form, setForm]               = useState({ title: "", due_date: "", course_id: "" });
  const [msg, setMsg]                 = useState(null);

  useEffect(() => {
    Promise.all([
      api.get(`/assignments/instructor/${user.id}`),
      api.get(`/courses/instructor/${user.id}`),
    ]).then(([a, c]) => {
      setAssignments(a);
      setCourses(c);
      if (c.length > 0) setForm(f => ({ ...f, course_id: c[0].course_id }));
    }).finally(() => setLoading(false));
  }, [user.id]);

  const create = async () => {
    if (!form.title || !form.due_date || !form.course_id) return;
    try {
      const a = await api.post("/assignments", { ...form, instructor_id: user.id });
      setAssignments(prev => [...prev, a]);
      setForm(f => ({ ...f, title: "", due_date: "" }));
      setMsg({ type: "ok", text: "Assignment created!" });
      setShowForm(false);
    } catch (e) {
      setMsg({ type: "err", text: e.message });
    }
  };

  return (
    <div style={{ maxWidth: 1280, margin: "0 auto", padding: "32px 32px" }}>
      {/* Create button */}
      <div style={{ marginBottom: 20, display: "flex", justifyContent: "flex-end" }}>
        <button onClick={() => setShowForm(f => !f)} style={{
          background: ACCENT, color: "#fff", border: "none", borderRadius: 3,
          padding: "9px 22px", fontSize: 14, fontWeight: 600, cursor: "pointer"
        }}>+ Create Assignment</button>
      </div>

      {showForm && (
        <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px", marginBottom: 20 }}>
          <div style={{ display: "flex", gap: 12, flexWrap: "wrap" }}>
            <input value={form.title} onChange={e => setForm(f => ({ ...f, title: e.target.value }))}
              placeholder="Assignment title"
              style={{ flex: 1, minWidth: 200, border: "1px solid #ccc", borderRadius: 3, padding: "9px 14px", fontSize: 14 }} />
            <input value={form.due_date} onChange={e => setForm(f => ({ ...f, due_date: e.target.value }))}
              type="date" style={{ border: "1px solid #ccc", borderRadius: 3, padding: "9px 14px", fontSize: 14 }} />
            <select value={form.course_id} onChange={e => setForm(f => ({ ...f, course_id: e.target.value }))}
              style={{ border: "1px solid #ccc", borderRadius: 3, padding: "9px 14px", fontSize: 14 }}>
              {courses.map(c => <option key={c.course_id} value={c.course_id}>{c.course_name}</option>)}
            </select>
            <button onClick={create} style={{
              background: ACCENT, color: "#fff", border: "none", borderRadius: 3,
              padding: "9px 22px", fontSize: 14, fontWeight: 600, cursor: "pointer"
            }}>Save</button>
          </div>
          {msg && <div style={{ fontSize: 13, color: msg.type === "ok" ? "#2e7d32" : "#c62828", marginTop: 10 }}>{msg.text}</div>}
        </div>
      )}

      <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px 28px" }}>
        <h2 style={{ margin: "0 0 24px", fontSize: 20, fontWeight: 600, color: "#222" }}>Assignments</h2>
        {loading ? <Spinner /> : (
          <table style={{ width: "100%", borderCollapse: "collapse", fontSize: 14 }}>
            <thead>
              <tr style={{ borderBottom: "2px solid #ddd" }}>
                {["Title", "Course", "Due Date"].map(h => (
                  <th key={h} style={{ padding: "10px 14px", textAlign: "left", color: "#555", fontWeight: 600, fontSize: 13 }}>{h}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {assignments.length === 0 && (
                <tr><td colSpan={3} style={{ padding: "40px 14px", color: "#999", textAlign: "center", fontSize: 15 }}>No assignments yet.</td></tr>
              )}
              {assignments.map(a => (
                <tr key={a.assignment_id} style={{ borderBottom: "1px solid #eee" }}
                  onMouseOver={e => e.currentTarget.style.background = "#fafafa"}
                  onMouseOut={e => e.currentTarget.style.background = "none"}>
                  <td style={{ padding: "14px 14px", fontWeight: 600, color: "#1a1a1a" }}>{a.title}</td>
                  <td style={{ padding: "14px 14px", color: "#555" }}>{a.course_name}</td>
                  <td style={{ padding: "14px 14px", color: "#555" }}>
                    {new Date(a.due_date).toLocaleDateString("en-IN", { day: "numeric", month: "short", year: "numeric" })}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

function GradePage({ user }) {
  const [submissions, setSubmissions] = useState([]);
  const [loading, setLoading]         = useState(true);
  const [grading, setGrading]         = useState({});
  const [msg, setMsg]                 = useState({});

  useEffect(() => {
    api.get(`/grades/instructor/${user.id}`).then(setSubmissions).finally(() => setLoading(false));
  }, [user.id]);

  const saveGrade = async (submission_id) => {
    const g = grading[submission_id];
    if (!g?.marks) return;
    try {
      await api.post("/grade", { submission_id, marks: Number(g.marks), feedback: g.feedback || "" });
      setSubmissions(prev => prev.map(s =>
        s.submission_id === submission_id ? { ...s, marks: Number(g.marks), feedback: g.feedback } : s
      ));
      setMsg(m => ({ ...m, [submission_id]: "ok" }));
    } catch (e) {
      setMsg(m => ({ ...m, [submission_id]: e.message }));
    }
  };

  return (
    <div style={{ maxWidth: 1280, margin: "0 auto", padding: "32px 32px" }}>
      <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px 28px" }}>
        <h2 style={{ margin: "0 0 24px", fontSize: 20, fontWeight: 600, color: "#222" }}>Grade Students</h2>
        {loading ? <Spinner /> : (
          <table style={{ width: "100%", borderCollapse: "collapse", fontSize: 14 }}>
            <thead>
              <tr style={{ borderBottom: "2px solid #ddd" }}>
                {["Student", "Assignment", "Course", "Submitted", "Marks", "Feedback", ""].map(h => (
                  <th key={h} style={{ padding: "10px 14px", textAlign: "left", color: "#555", fontWeight: 600, fontSize: 13 }}>{h}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {submissions.length === 0 && (
                <tr><td colSpan={7} style={{ padding: "40px 14px", color: "#999", textAlign: "center", fontSize: 15 }}>No submissions yet.</td></tr>
              )}
              {submissions.map(s => (
                <tr key={s.submission_id} style={{ borderBottom: "1px solid #eee" }}
                  onMouseOver={e => e.currentTarget.style.background = "#fafafa"}
                  onMouseOut={e => e.currentTarget.style.background = "none"}>
                  <td style={{ padding: "12px 14px", fontWeight: 600, color: "#1a1a1a" }}>{s.student_name}</td>
                  <td style={{ padding: "12px 14px", color: "#555" }}>{s.assignment_title}</td>
                  <td style={{ padding: "12px 14px", color: "#555" }}>{s.course_name}</td>
                  <td style={{ padding: "12px 14px", color: "#777", fontSize: 13 }}>
                    {new Date(s.submit_date).toLocaleDateString("en-IN")}
                  </td>
                  <td style={{ padding: "12px 14px" }}>
                    {s.marks != null ? (
                      <span style={{
                        background: s.marks >= 85 ? "#e8f5e9" : s.marks >= 60 ? "#fff3e0" : "#ffebee",
                        color: s.marks >= 85 ? "#2e7d32" : s.marks >= 60 ? "#e65100" : "#c62828",
                        padding: "3px 12px", borderRadius: 12, fontWeight: 700, fontSize: 13
                      }}>{s.marks}</span>
                    ) : (
                      <input type="number" min="0" max="100" placeholder="0-100"
                        value={grading[s.submission_id]?.marks || ""}
                        onChange={e => setGrading(g => ({ ...g, [s.submission_id]: { ...g[s.submission_id], marks: e.target.value } }))}
                        style={{ width: 76, border: "1px solid #ccc", borderRadius: 3, padding: "5px 9px", fontSize: 13 }} />
                    )}
                  </td>
                  <td style={{ padding: "12px 14px" }}>
                    {s.marks != null ? (
                      <span style={{ color: "#777", fontStyle: "italic", fontSize: 13 }}>{s.feedback || "—"}</span>
                    ) : (
                      <input type="text" placeholder="Feedback"
                        value={grading[s.submission_id]?.feedback || ""}
                        onChange={e => setGrading(g => ({ ...g, [s.submission_id]: { ...g[s.submission_id], feedback: e.target.value } }))}
                        style={{ width: "100%", border: "1px solid #ccc", borderRadius: 3, padding: "5px 9px", fontSize: 13 }} />
                    )}
                  </td>
                  <td style={{ padding: "12px 14px" }}>
                    {s.marks == null && (
                      <button onClick={() => saveGrade(s.submission_id)} style={{
                        background: ACCENT, color: "#fff", border: "none", borderRadius: 3,
                        padding: "5px 14px", fontSize: 13, fontWeight: 600, cursor: "pointer"
                      }}>Save</button>
                    )}
                    {msg[s.submission_id] === "ok" && <span style={{ color: "#2e7d32", fontSize: 14 }}>✓</span>}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

function Spinner() {
  return (
    <div style={{ display: "flex", justifyContent: "center", padding: "48px 0" }}>
      <div style={{
        width: 36, height: 36, border: "3px solid #ddd",
        borderTopColor: ACCENT, borderRadius: "50%",
        animation: "spin 0.8s linear infinite"
      }} />
      <style>{`@keyframes spin { to { transform: rotate(360deg); } }`}</style>
    </div>
  );
}
