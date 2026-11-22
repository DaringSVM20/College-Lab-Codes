import { useState } from "react";
import Login from "./pages/Login";
import StudentDashboard from "./pages/StudentDashboard";
import InstructorDashboard from "./pages/InstructorDashboard";

export default function App() {
  const [user, setUser] = useState(null);

  if (!user) return <Login onLogin={setUser} />;
  if (user.role === "instructor") return <InstructorDashboard user={user} onLogout={() => setUser(null)} />;
  return <StudentDashboard user={user} onLogout={() => setUser(null)} />;
}
