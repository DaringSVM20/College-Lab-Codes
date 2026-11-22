export default function Layout({ user, onLogout, navItems, activeTab, setActiveTab, children, accentColor = "indigo" }) {
  const avatarBg = {
    indigo:  "bg-indigo-600",
    emerald: "bg-emerald-600",
    orange:  "bg-orange-500",
    green:   "bg-green-800",
  }[accentColor] ?? "bg-indigo-600";

  return (
    <div className="min-h-screen bg-slate-950 flex">
      {/* Sidebar */}
      <aside
        className="w-64 border-r flex flex-col"
        style={{ backgroundColor: "var(--sidebar-bg, #0f172a)", borderColor: "var(--sidebar-border, #1e293b)" }}>

        {/* Brand */}
        <div className="p-6" style={{ borderBottom: "1px solid var(--sidebar-border, #1e293b)" }}>
          <div className="flex items-center gap-3">
            <div className={`w-8 h-8 rounded-lg ${avatarBg} flex items-center justify-center`}>
              <svg className="w-4 h-4 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                  d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253" />
              </svg>
            </div>
            <div>
              <p className="text-white font-semibold text-sm">CourseMS</p>
              <p className="text-slate-500 text-xs capitalize">{user.role} Portal</p>
            </div>
          </div>
        </div>

        {/* Nav */}
        <nav className="flex-1 p-4 space-y-1">
          {navItems.map(({ id, label, icon }) => (
            <button
              key={id}
              onClick={() => setActiveTab(id)}
              className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl text-sm font-medium transition-all
                ${activeTab === id ? "text-white shadow-md" : "text-slate-400 hover:text-white hover:bg-slate-800"}`}
              style={activeTab === id ? { backgroundColor: "var(--active-bg, #4f46e5)" } : {}}>
              <span className="text-lg">{icon}</span>
              {label}
            </button>
          ))}
        </nav>

        {/* User */}
        <div className="p-4" style={{ borderTop: "1px solid var(--sidebar-border, #1e293b)" }}>
          <div className="flex items-center gap-3 mb-3">
            <div className={`w-9 h-9 rounded-full ${avatarBg} flex items-center justify-center text-white text-sm font-bold`}>
              {user.name.charAt(0)}
            </div>
            <div className="flex-1 min-w-0">
              <p className="text-white text-sm font-medium truncate">{user.name}</p>
              <p className="text-slate-500 text-xs truncate">{user.email}</p>
            </div>
          </div>
          <button onClick={onLogout}
            className="w-full text-xs text-slate-500 hover:text-red-400 border border-slate-800 hover:border-red-500/30 rounded-lg py-2 transition-all">
            Sign out
          </button>
        </div>
      </aside>

      {/* Main */}
      <main className="flex-1 overflow-auto">
        <div className="p-8">{children}</div>
      </main>
    </div>
  );
}
