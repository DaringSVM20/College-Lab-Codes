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

const SEM_TABS = ["All"];
const ORANGE = "#E87722";

export default function StudentDashboard({ user, onLogout }) {
  const [page, setPage] = useState("courses");

  return (
    <div style={{ fontFamily: "'Segoe UI', Arial, sans-serif", background: "#f0f0f0", minHeight: "100vh" }}>
      <TopNav user={user} onLogout={onLogout} />
      <OrangeNav page={page} setPage={setPage} />
      {page === "courses"     && <CoursesPage     user={user} />}
      {page === "assignments" && <AssignmentsPage user={user} />}
      {page === "grades"      && <GradesPage      user={user} />}
    </div>
  );
}

// ── Top White Navbar ──────────────────────────────────────────
function TopNav({ user, onLogout }) {
  return (
    <div style={{
      background: "#fff", borderBottom: "1px solid #ddd",
      display: "flex", alignItems: "center", justifyContent: "space-between",
      padding: "0 32px", height: "68px", position: "sticky", top: 0, zIndex: 100,
      boxShadow: "0 1px 4px rgba(0,0,0,0.08)"
    }}>
      {/* Logo + Title */}
      <div style={{ display: "flex", alignItems: "center", gap: "16px" }}>
        <div style={{
          width: 44, height: 44, borderRadius: "50%",
          background: "#8B1A1A", display: "flex", alignItems: "center",
          justifyContent: "center", flexShrink: 0
        }}>
          <span style={{ color: "#fff", fontSize: 15, fontWeight: 700 }}>M</span>
        </div>
        <div style={{ width: 1, height: 36, background: "#ddd" }} />
        <span style={{ fontSize: 20, fontWeight: 600, color: "#222" }}>MIT Manipal</span>
      </div>

      {/* Right icons + user */}
      <div style={{ display: "flex", alignItems: "center", gap: "22px" }}>
        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#555" strokeWidth="2">
          <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
          <rect x="3" y="14" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/>
        </svg>
        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#555" strokeWidth="2">
          <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
          <polyline points="22,6 12,13 2,6"/>
        </svg>
        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#555" strokeWidth="2">
          <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"/>
        </svg>
        <div style={{ position: "relative" }}>
          <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#555" strokeWidth="2">
            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/>
            <path d="M13.73 21a2 2 0 0 1-3.46 0"/>
          </svg>
          <div style={{
            position: "absolute", top: -4, right: -4, width: 9, height: 9,
            background: ORANGE, borderRadius: "50%", border: "2px solid #fff"
          }} />
        </div>
        <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
          <div style={{
            width: 36, height: 36, borderRadius: "4px",
            background: "#8B1A1A", display: "flex", alignItems: "center",
            justifyContent: "center", color: "#fff", fontWeight: 700, fontSize: 14
          }}>
            {user.name.slice(0, 2).toUpperCase()}
          </div>
          <span style={{ fontSize: 14, fontWeight: 600, color: "#333" }}>
            {user.name.toUpperCase()} {user.id}
          </span>
        </div>
        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="#555" strokeWidth="2">
          <circle cx="12" cy="12" r="3"/>
          <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1-2.83 2.83l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-4 0v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83-2.83l.06-.06A1.65 1.65 0 0 0 4.68 15a1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1 0-4h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 2.83-2.83l.06.06A1.65 1.65 0 0 0 9 4.68a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 4 0v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 2.83l-.06.06A1.65 1.65 0 0 0 19.4 9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 0 4h-.09a1.65 1.65 0 0 0-1.51 1z"/>
        </svg>
        <button onClick={onLogout} style={{
          fontSize: 13, color: "#888", background: "none", border: "none",
          cursor: "pointer", marginLeft: 4, padding: "5px 10px",
          borderRadius: 4, transition: "background 0.15s"
        }}
          onMouseOver={e => e.target.style.background = "#f0f0f0"}
          onMouseOut={e => e.target.style.background = "none"}>
          Sign out
        </button>
      </div>
    </div>
  );
}

// ── Orange Navigation Bar ─────────────────────────────────────
function OrangeNav({ page, setPage }) {
  // Only functional pages — no dummy links
  const links = [
    { id: "courses",     label: "My Courses" },
    { id: "assignments", label: "Assignments" },
    { id: "grades",      label: "My Grades" },
  ];

  return (
    <div style={{ background: ORANGE, padding: "0 32px", display: "flex" }}>
      {links.map(l => (
        <button key={l.id} onClick={() => setPage(l.id)}
          style={{
            background: "none", border: "none", cursor: "pointer",
            color: "#fff", fontSize: 15, fontWeight: 400,
            padding: "14px 20px", position: "relative",
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

// ── Courses Page ──────────────────────────────────────────────
function CoursesPage({ user }) {
  const [courses, setCourses]   = useState([]);
  const [all, setAll]           = useState([]);
  const [enrolled, setEnrolled] = useState(new Set());
  const [loading, setLoading]   = useState(true);
  const [semTab, setSemTab]     = useState("All");
  const [section, setSection]   = useState("enrolled");
  const [msg, setMsg]           = useState({});

  useEffect(() => {
    Promise.all([
      api.get(`/courses/student/${user.id}`),
      api.get("/courses"),
    ]).then(([mine, all]) => {
      setCourses(mine);
      setAll(all);
      setEnrolled(new Set(mine.map(c => c.course_id)));
    }).finally(() => setLoading(false));
  }, [user.id]);

  const enroll = async (course_id) => {
    try {
      await api.post("/enroll", { student_id: user.id, course_id });
      const newCourse = all.find(c => c.course_id === course_id);
      setEnrolled(prev => new Set([...prev, course_id]));
      setCourses(prev => [...prev, newCourse]);
      setMsg(m => ({ ...m, [course_id]: "Enrolled!" }));
    } catch (e) {
      setMsg(m => ({ ...m, [course_id]: e.message }));
    }
  };

  const displayCourses = section === "enrolled" ? courses : all;

  return (
    <div style={{ maxWidth: 1280, margin: "0 auto", padding: "0 32px 48px" }}>
      {/* Hero Banner */}
      <div style={{
        position: "relative", height: 200, marginBottom: 32, overflow: "hidden",
        borderRadius: "0 0 4px 4px"
      }}>
        <img
          src="https://images.unsplash.com/photo-1518770660439-4636190af475?w=1400&q=80"
          alt="MIT Manipal"
          style={{ width: "100%", height: "100%", objectFit: "cover", objectPosition: "center 40%" }}
        />
        <div style={{
          position: "absolute", inset: 0,
          background: "linear-gradient(to right, rgba(0,0,0,0.6) 0%, rgba(0,0,0,0.15) 100%)"
        }} />
        <div style={{
          position: "absolute", bottom: 28, left: 32,
          color: "#fff", fontSize: 32, fontWeight: 400, letterSpacing: 0.3
        }}>
          Manipal Institute of Technology
        </div>
      </div>

      {/* Section toggle */}
      <div style={{ display: "flex", gap: 12, marginBottom: 20 }}>
        {["enrolled", "browse"].map(s => (
          <button key={s} onClick={() => setSection(s)} style={{
            background: section === s ? ORANGE : "#fff",
            color: section === s ? "#fff" : "#555",
            border: "1px solid #ccc", borderRadius: 4,
            padding: "8px 22px", fontSize: 14, cursor: "pointer", fontWeight: 500,
          }}>
            {s === "enrolled" ? "My Courses" : "Browse & Enroll"}
          </button>
        ))}
      </div>

      {/* White card container */}
      <div style={{
        background: "#fff", border: "1px solid #ddd",
        borderRadius: 4, padding: "24px 24px 36px"
      }}>
        {/* Card header */}
        <div style={{ display: "flex", alignItems: "center", gap: 8, marginBottom: 18 }}>
          <span style={{ fontSize: 18, fontWeight: 600, color: "#222" }}>
            {section === "enrolled" ? "My Courses" : "All Courses"}
          </span>
          <span style={{ fontSize: 15, color: "#888" }}>▾</span>
        </div>

        {/* Semester filter tabs */}
        <div style={{ display: "flex", borderBottom: "1px solid #ddd", marginBottom: 28 }}>
          {SEM_TABS.map(t => (
            <button key={t} onClick={() => setSemTab(t)} style={{
              background: "none", border: "none", cursor: "pointer",
              fontSize: 14, color: semTab === t ? "#333" : "#666",
              fontWeight: semTab === t ? 600 : 400,
              padding: "10px 20px 12px",
              borderBottom: semTab === t ? "3px solid #1565c0" : "3px solid transparent",
              marginBottom: -1, transition: "border-color 0.15s",
            }}>
              {t}
            </button>
          ))}
        </div>

        {/* Course Grid */}
        {loading ? <Spinner /> : (
          <div style={{
            display: "grid",
            gridTemplateColumns: "repeat(4, 1fr)",
            gap: "20px",
          }}>
            {displayCourses.length === 0 && (
              <div style={{ gridColumn: "1/-1", textAlign: "center", color: "#999", padding: "48px 0", fontSize: 15 }}>
                {section === "enrolled" ? "No courses enrolled yet. Switch to Browse & Enroll." : "No courses available."}
              </div>
            )}
            {displayCourses.map((c, idx) => {
              const isEnrolled = enrolled.has(c.course_id);
              return (
                <div key={c.course_id} style={{
                  background: "#fff", border: "1px solid #ddd",
                  borderRadius: 2, overflow: "hidden",
                  cursor: "pointer", transition: "box-shadow 0.2s",
                  boxShadow: "0 1px 3px rgba(0,0,0,0.06)",
                }}
                  onMouseOver={e => e.currentTarget.style.boxShadow = "0 4px 14px rgba(0,0,0,0.15)"}
                  onMouseOut={e => e.currentTarget.style.boxShadow = "0 1px 3px rgba(0,0,0,0.06)"}>
                  {/* Course image */}
                  <div style={{ height: 155, overflow: "hidden" }}>
                    <img
                      src={THUMBS[idx % THUMBS.length]}
                      alt={c.course_name}
                      style={{ width: "100%", height: "100%", objectFit: "cover" }}
                    />
                  </div>
                  {/* Card body */}
                  <div style={{ padding: "14px 16px 16px" }}>
                    <div style={{
                      fontSize: 14, fontWeight: 700, color: "#1a1a1a",
                      lineHeight: 1.4, marginBottom: 7,
                    }}>
                      {c.course_name}
                    </div>
                    <div style={{ fontSize: 12, color: "#666", marginBottom: 5 }}>
                      009_CMS_{c.course_id}_IV_2025-2026
                    </div>
                    <div style={{ display: "flex", alignItems: "center", gap: 5, fontSize: 12, color: "#666" }}>
                      <span style={{ width: 5, height: 5, background: "#666", borderRadius: "50%", flexShrink: 0 }} />
                      AY 2025-2026 | Sem IV | {c.credits} credits
                    </div>
                    {section === "browse" && (
                      <button
                        onClick={() => enroll(c.course_id)}
                        disabled={isEnrolled}
                        style={{
                          marginTop: 12, width: "100%", padding: "8px 0",
                          background: isEnrolled ? "#e0e0e0" : ORANGE,
                          color: isEnrolled ? "#999" : "#fff",
                          border: "none", borderRadius: 3,
                          fontSize: 13, fontWeight: 600, cursor: isEnrolled ? "not-allowed" : "pointer",
                        }}>
                        {isEnrolled ? "✓ Enrolled" : "Enroll"}
                      </button>
                    )}
                    {msg[c.course_id] && (
                      <div style={{ fontSize: 12, color: isEnrolled ? "#2e7d32" : "#c62828", marginTop: 5 }}>
                        {msg[c.course_id]}
                      </div>
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>
    </div>
  );
}

// ── Assignments Page ──────────────────────────────────────────
function AssignmentsPage({ user }) {
  const [assignments, setAssignments] = useState([]);
  const [loading, setLoading]         = useState(true);
  const [msg, setMsg]                 = useState({});

  useEffect(() => {
    api.get(`/assignments/student/${user.id}`).then(setAssignments).finally(() => setLoading(false));
  }, [user.id]);

  const submit = async (assignment_id) => {
    try {
      await api.post("/submit", { student_id: user.id, assignment_id });
      setAssignments(prev => prev.map(a => a.assignment_id === assignment_id ? { ...a, submitted: 1 } : a));
      setMsg(m => ({ ...m, [assignment_id]: "ok" }));
    } catch (e) {
      setMsg(m => ({ ...m, [assignment_id]: e.message }));
    }
  };

  const today = new Date();

  return (
    <div style={{ maxWidth: 1280, margin: "0 auto", padding: "32px 32px" }}>
      <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px 28px" }}>
        <h2 style={{ margin: "0 0 24px", fontSize: 20, fontWeight: 600, color: "#222" }}>Assignments</h2>
        {loading ? <Spinner /> : (
          <table style={{ width: "100%", borderCollapse: "collapse", fontSize: 14 }}>
            <thead>
              <tr style={{ borderBottom: "2px solid #ddd" }}>
                {["Assignment", "Course", "Due Date", "Status", ""].map(h => (
                  <th key={h} style={{ padding: "10px 14px", textAlign: "left", color: "#555", fontWeight: 600, fontSize: 13 }}>{h}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {assignments.length === 0 && (
                <tr><td colSpan={5} style={{ padding: "40px 14px", color: "#999", textAlign: "center", fontSize: 15 }}>No assignments found.</td></tr>
              )}
              {assignments.map(a => {
                const due = new Date(a.due_date).toLocaleDateString("en-IN", { day: "numeric", month: "short", year: "numeric" });
                const isOverdue = new Date(a.due_date) < today && !a.submitted;
                const statusColor = a.submitted ? "#2e7d32" : isOverdue ? "#c62828" : "#e65100";
                const statusBg    = a.submitted ? "#e8f5e9"  : isOverdue ? "#ffebee"  : "#fff3e0";
                const statusText  = a.submitted ? "Submitted" : isOverdue ? "Overdue" : "Pending";
                return (
                  <tr key={a.assignment_id} style={{ borderBottom: "1px solid #eee" }}
                    onMouseOver={e => e.currentTarget.style.background = "#fafafa"}
                    onMouseOut={e => e.currentTarget.style.background = "none"}>
                    <td style={{ padding: "14px 14px", fontWeight: 600, color: "#1a1a1a" }}>{a.title}</td>
                    <td style={{ padding: "14px 14px", color: "#555" }}>{a.course_name}</td>
                    <td style={{ padding: "14px 14px", color: "#555" }}>{due}</td>
                    <td style={{ padding: "14px 14px" }}>
                      <span style={{
                        background: statusBg, color: statusColor,
                        padding: "4px 12px", borderRadius: 12, fontSize: 12, fontWeight: 600
                      }}>{statusText}</span>
                    </td>
                    <td style={{ padding: "14px 14px" }}>
                      {!a.submitted && (
                        <button onClick={() => submit(a.assignment_id)} style={{
                          background: ORANGE, color: "#fff", border: "none",
                          borderRadius: 3, padding: "6px 16px", fontSize: 13,
                          fontWeight: 600, cursor: "pointer"
                        }}>Submit</button>
                      )}
                      {msg[a.assignment_id] === "ok" && <span style={{ color: "#2e7d32", fontSize: 14 }}>✓</span>}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

// ── Grades Page ───────────────────────────────────────────────
function GradesPage({ user }) {
  const [grades, setGrades]   = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get(`/grades/student/${user.id}`).then(setGrades).finally(() => setLoading(false));
  }, [user.id]);

  const graded = grades.filter(g => g.marks != null);
  const avg = graded.length > 0 ? graded.reduce((s, g) => s + g.marks, 0) / graded.length : null;

  return (
    <div style={{ maxWidth: 1280, margin: "0 auto", padding: "32px 32px" }}>
      {avg !== null && (
        <div style={{
          background: "#fff3e0", border: "1px solid #ffe0b2", borderRadius: 4,
          padding: "18px 24px", marginBottom: 20, display: "flex", alignItems: "center", gap: 18
        }}>
          <div style={{
            width: 60, height: 60, borderRadius: 4,
            background: ORANGE, display: "flex", alignItems: "center",
            justifyContent: "center", color: "#fff", fontSize: 22, fontWeight: 700, flexShrink: 0
          }}>
            {avg.toFixed(0)}
          </div>
          <div>
            <div style={{ fontWeight: 600, color: "#222", fontSize: 17 }}>Overall Average</div>
            <div style={{ color: "#777", fontSize: 14 }}>{graded.length} graded submission{graded.length !== 1 ? "s" : ""}</div>
          </div>
        </div>
      )}
      <div style={{ background: "#fff", border: "1px solid #ddd", borderRadius: 4, padding: "24px 28px" }}>
        <h2 style={{ margin: "0 0 24px", fontSize: 20, fontWeight: 600, color: "#222" }}>My Grades</h2>
        {loading ? <Spinner /> : (
          <table style={{ width: "100%", borderCollapse: "collapse", fontSize: 14 }}>
            <thead>
              <tr style={{ borderBottom: "2px solid #ddd" }}>
                {["Assignment", "Course", "Marks", "Feedback"].map(h => (
                  <th key={h} style={{ padding: "10px 14px", textAlign: "left", color: "#555", fontWeight: 600, fontSize: 13 }}>{h}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {grades.length === 0 && (
                <tr><td colSpan={4} style={{ padding: "40px 14px", color: "#999", textAlign: "center", fontSize: 15 }}>No grades yet.</td></tr>
              )}
              {grades.map((g, i) => {
                const mc = g.marks == null ? "#999" : g.marks >= 85 ? "#2e7d32" : g.marks >= 60 ? "#e65100" : "#c62828";
                const mb = g.marks == null ? "#f5f5f5" : g.marks >= 85 ? "#e8f5e9" : g.marks >= 60 ? "#fff3e0" : "#ffebee";
                return (
                  <tr key={i} style={{ borderBottom: "1px solid #eee" }}
                    onMouseOver={e => e.currentTarget.style.background = "#fafafa"}
                    onMouseOut={e => e.currentTarget.style.background = "none"}>
                    <td style={{ padding: "14px 14px", fontWeight: 600, color: "#1a1a1a" }}>{g.assignment_title}</td>
                    <td style={{ padding: "14px 14px", color: "#555" }}>{g.course_name}</td>
                    <td style={{ padding: "14px 14px" }}>
                      <span style={{
                        background: mb, color: mc, padding: "4px 14px",
                        borderRadius: 12, fontSize: 13, fontWeight: 700
                      }}>
                        {g.marks != null ? g.marks : "Pending"}
                      </span>
                    </td>
                    <td style={{ padding: "14px 14px", color: "#777", fontStyle: g.feedback ? "italic" : "normal", fontSize: 14 }}>
                      {g.feedback || "—"}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

// ── Shared ────────────────────────────────────────────────────
function Spinner() {
  return (
    <div style={{ display: "flex", justifyContent: "center", padding: "48px 0" }}>
      <div style={{
        width: 36, height: 36, border: "3px solid #ddd",
        borderTopColor: ORANGE, borderRadius: "50%",
        animation: "spin 0.8s linear infinite"
      }} />
      <style>{`@keyframes spin { to { transform: rotate(360deg); } }`}</style>
    </div>
  );
}
