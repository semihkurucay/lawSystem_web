<!DOCTYPE html>
<html lang="tr">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Hukuk Bürosu Yönetim Sistemi</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
      <style>
         body { background-color: #f8f9fa; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
         /* Sidebar */
         .sidebar { 
         min-height: 100vh; background-color: #2c3e50; color: white; position: fixed; width: 250px; 
         display: flex; flex-direction: column; justify-content: space-between; z-index: 1000;
         }
         .sidebar-menu a { 
         color: #bdc3c7; text-decoration: none; padding: 12px; display: block; 
         border-radius: 4px; margin-bottom: 5px; transition: 0.2s; cursor: pointer;
         }
         .sidebar-menu a:hover { background-color: #34495e; color: white; padding-left: 15px; }
         .sidebar-menu a.active { background-color: #1abc9c; color: white; font-weight: bold; }
         .sidebar-footer { border-top: 1px solid #34495e; padding: 15px; background-color: #243342; }
         .sidebar-footer a { 
         color: #bdc3c7; text-decoration: none; padding: 10px 5px; display: block; 
         border-radius: 4px; transition: 0.2s; cursor: pointer; font-size: 0.95em;
         }
         .sidebar-footer a:hover { color: white; }
         /* Main Content */
         .main-content { margin-left: 250px; padding: 20px; }
         .card-custom { border: none; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
         .status-badge { padding: 5px 15px; border-radius: 20px; font-size: 0.85em; font-weight: 600; display: inline-block; min-width: 80px; text-align: center; }
         .status-aktif { background-color: #e8f5e9; color: #2e7d32; border: 1px solid #c8e6c9; } 
         .status-kapali { background-color: #ffebee; color: #c62828; border: 1px solid #ffcdd2; }
         .page-section { display: none; animation: fadeIn 0.3s; }
         .page-section.active { display: block; }
         @keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }
         .timeline { border-left: 2px solid #e9ecef; margin-left: 10px; padding-left: 20px; list-style: none; margin-top: 20px; }
         .timeline-item { position: relative; margin-bottom: 20px; }
         .timeline-item::before { content: ''; position: absolute; left: -26px; top: 5px; width: 14px; height: 14px; border-radius: 50%; background: #007bff; border: 2px solid white; box-shadow: 0 0 0 1px #007bff; }
         /* Takvim */
         .calendar-grid { display: grid; grid-template-columns: repeat(7, 1fr); gap: 5px; }
         .calendar-day { 
         min-height: 100px; background: white; border: 1px solid #dee2e6; 
         padding: 5px; border-radius: 4px; cursor: pointer; transition: all 0.2s; position: relative;
         }
         .calendar-day:hover { background-color: #f1f3f5; border-color: #adb5bd; }
         .calendar-day.selected { border: 2px solid #0d6efd; background-color: #e7f1ff; }
         /* BUGÜNÜN TARİHİ İÇİN ÖZEL STİL */
         .calendar-day.today { border: 2px solid #1abc9c; background-color: #e0f2f1; }
         .calendar-day.today::after {
         content: 'Bugün'; position: absolute; top: 2px; right: 5px; 
         font-size: 0.6em; color: #1abc9c; font-weight: bold;
         }
         .event-badge { font-size: 0.7em; padding: 2px 4px; border-radius: 3px; display: block; margin-top: 2px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
         .event-active { background-color: #d1e7dd; color: #0f5132; border: 1px solid #badbcc; } 
         .event-passive { background-color: #f8d7da; color: #842029; border: 1px solid #f5c2c7; } 
         .day-header { text-align: center; font-weight: bold; padding: 5px; color: #6c757d; }
      </style>
   </head>
   <body>
      <div class="sidebar">
         <div class="p-3 sidebar-menu">
            <h4 class="text-center mb-4"><i class="fa-solid fa-scale-balanced"></i> HukukSis</h4>
            <a onclick="switchPage('dashboard', this)" class="menu-link active"><i class="fa-solid fa-chart-line me-2"></i> Dashboard</a>
            <a onclick="switchPage('cases', this)" class="menu-link"><i class="fa-solid fa-folder-open me-2"></i> Davalar</a>
            <a onclick="switchPage('clients', this)" class="menu-link"><i class="fa-solid fa-users me-2"></i> Müvekkiller</a>
            <a onclick="switchPage('calendar', this)" class="menu-link"><i class="fa-solid fa-calendar-days me-2"></i> Takvim</a>
         </div>
         <div class="sidebar-footer">
            <div class="text-white-50 small mb-2 px-1">Giriş: <span id="lblUserName" class="text-white fw-bold">...</span></div>
            <a onclick="openSettingsModal()" class="mb-1"><i class="fa-solid fa-gear"></i> Ayarlar</a>
            <a onclick="logoutSystem()" class="text-danger"><i class="fa-solid fa-right-from-bracket"></i> Çıkış Yap</a>
         </div>
      </div>
      <div class="main-content">
         <div class="d-flex justify-content-between align-items-center bg-white p-3 rounded shadow-sm mb-4">
            <h5 class="mb-0 text-muted" id="pageTitle">Dashboard</h5>
            <div id="globalSearchContainer" style="display:none; width: 300px;">
               <input type="text" id="globalSearchInput" class="form-control" placeholder="Ara..." onkeyup="globalSearch()">
            </div>
         </div>
         <div id="page-dashboard" class="page-section active">
            <div class="row">
               <div class="col-md-4">
                  <div class="card card-custom p-3 bg-white">
                     <h3><i class="fa-solid fa-folder text-primary"></i> <span id="statTotalCases">-</span></h3>
                     <small>Toplam Dava</small>
                  </div>
               </div>
               <div class="col-md-4">
                  <div class="card card-custom p-3 bg-white">
                     <h3><i class="fa-solid fa-gavel text-danger"></i> <span id="statUpcoming">-</span></h3>
                     <small>Yaklaşan Duruşma</small>
                  </div>
               </div>
               <div class="col-md-4">
                  <div class="card card-custom p-3 bg-white">
                     <h3><i class="fa-solid fa-file-contract text-success"></i> <span id="statActiveCases">-</span></h3>
                     <small>Aktif Dosya</small>
                  </div>
               </div>
            </div>
         </div>
         <div id="page-cases" class="page-section">
            <div class="card card-custom p-4 bg-white">
               <div class="d-flex justify-content-between mb-3">
                  <h4><i class="fa-solid fa-folder text-primary"></i> Dava Dosyaları</h4>
                  <button class="btn btn-primary btn-sm" onclick="openAddCaseModal()"><i class="fa-solid fa-plus"></i> Yeni Dava Ekle</button>
               </div>
               <table class="table table-hover align-middle">
                  <thead>
                     <tr>
                        <th>Dosya No</th>
                        <th>Müvekkil</th>
                        <th>Dava Türü</th>
                        <th>Duruşma</th>
                        <th>Durum</th>
                        <th>İşlem</th>
                     </tr>
                  </thead>
                  <tbody id="caseTableBody"></tbody>
               </table>
               <div id="noCaseResult" class="d-none text-center p-3 text-muted">Kayıt bulunamadı.</div>
            </div>
         </div>
         <div id="page-clients" class="page-section">
            <div class="card card-custom p-4 bg-white">
               <div class="d-flex justify-content-between mb-3">
                  <h4><i class="fa-solid fa-users text-primary"></i> Müvekkil Listesi</h4>
                  <button class="btn btn-success btn-sm" onclick="openAddClientModal()"><i class="fa-solid fa-user-plus"></i> Yeni Müvekkil</button>
               </div>
               <table class="table table-hover align-middle">
                  <thead>
                     <tr>
                        <th>TC/VKN</th>
                        <th>İsim</th>
                        <th>Tip</th>
                        <th>Telefon</th>
                        <th>İşlem</th>
                     </tr>
                  </thead>
                  <tbody id="clientTableBody"></tbody>
               </table>
            </div>
         </div>
         <div id="page-calendar" class="page-section">
            <div class="card card-custom p-4 bg-white">
               <div class="d-flex justify-content-between align-items-center mb-4">
                  <h4 class="text-muted">Duruşma Takvimi</h4>
               </div>
               <div class="row">
                  <div class="col-lg-8">
                     <div class="d-flex justify-content-between align-items-center mb-3">
                        <button class="btn btn-sm btn-outline-secondary" onclick="changeMonth(-1)"><i class="fa fa-chevron-left"></i></button>
                        <h5 id="currentMonthYear" class="m-0 fw-bold">Ocak 2026</h5>
                        <button class="btn btn-sm btn-outline-secondary" onclick="changeMonth(1)"><i class="fa fa-chevron-right"></i></button>
                     </div>
                     <div class="calendar-grid mb-2">
                        <div class="day-header">Pzt</div>
                        <div class="day-header">Sal</div>
                        <div class="day-header">Çar</div>
                        <div class="day-header">Per</div>
                        <div class="day-header">Cum</div>
                        <div class="day-header">Cmt</div>
                        <div class="day-header text-danger">Paz</div>
                     </div>
                     <div id="calendarDays" class="calendar-grid"></div>
                  </div>
                  <div class="col-lg-4">
                     <div class="card border-0 shadow-sm h-100" style="background-color: #fcfcfc;">
                        <div class="card-header bg-white border-bottom">
                           <h6 class="mb-0" id="selectedDateTitle">Seçili Gün: ...</h6>
                        </div>
                        <div class="card-body p-2" id="selectedDayList" style="max-height: 600px; overflow-y: auto;">
                           <div class="text-center text-muted mt-5">
                              <i class="fa-solid fa-spinner fa-spin fa-2x mb-2"></i>
                              <p>Yükleniyor...</p>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="settingsModal" tabindex="-1">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title"><i class="fa-solid fa-user-gear"></i> Kullanıcı Profili</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
               </div>
               <div class="modal-body">
                  <form id="userSettingsForm">
                     <div class="row mb-3">
                        <div class="col-6"><label class="form-label small fw-bold">Ad</label><input type="text" class="form-control" id="settingsName"></div>
                        <div class="col-6"><label class="form-label small fw-bold">Soyad</label><input type="text" class="form-control" id="settingsSurname"></div>
                     </div>
                     <div class="mb-3"><label class="form-label small fw-bold">E-Posta</label><input type="email" class="form-control" id="settingsMail"></div>
                     <hr>
                     <div class="mb-3"><label class="form-label small fw-bold text-danger">Yeni Şifre</label><input type="password" class="form-control" id="settingsPassword" placeholder="******"><small class="text-muted" style="font-size: 0.75em;">Şifre değiştirmeyecekseniz boş bırakın.</small></div>
                  </form>
               </div>
               <div class="modal-footer"><button class="btn btn-primary" onclick="updateUserSettings()">Profili Güncelle</button></div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="caseDetailModal" tabindex="-1">
         <div class="modal-dialog modal-lg">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title"><i class="fa-solid fa-folder"></i> <span id="modalFileNumber"></span></h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
               </div>
               <div class="modal-body">
                  <ul class="nav nav-tabs mb-3">
                     <li class="nav-item"><button class="nav-link active" data-bs-toggle="tab" data-bs-target="#tab-general">📝 Genel Bilgiler</button></li>
                     <li class="nav-item"><button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-history">⏱️ Süreç Geçmişi</button></li>
                     <li class="nav-item"><button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-files">📎 Dosyalar</button></li>
                  </ul>
                  <div class="tab-content">
                     <div class="tab-pane fade show active" id="tab-general">
                        <div id="view-mode">
                           <div class="d-flex justify-content-end mb-2"><button id="btnCloseCase" class="btn btn-outline-danger btn-sm me-2" onclick="closeCaseProcess()"><i class="fa-solid fa-gavel"></i> Davayı Kapat</button><button id="btnEditCase" class="btn btn-outline-primary btn-sm" onclick="toggleEditMode(true)"><i class="fa-solid fa-pen"></i> Düzenle</button></div>
                           <div class="row mb-3">
                              <div class="col-6">
                                 <label class="text-muted small fw-bold">Müvekkil</label>
                                 <div class="fs-5 fw-bold" id="viewClient">-</div>
                              </div>
                              <div class="col-6">
                                 <label class="text-muted small fw-bold">Mahkeme</label>
                                 <div class="fs-5" id="viewCourt">-</div>
                              </div>
                           </div>
                           <div class="row mb-3 bg-light p-3 rounded mx-0">
                              <div class="col-6">
                                 <label class="text-danger small fw-bold">Sonraki Duruşma</label>
                                 <div class="fs-5" id="viewNextHearing">-</div>
                              </div>
                              <div class="col-6">
                                 <label class="text-muted small fw-bold">Durum</label>
                                 <div id="viewStatus" class="mt-1"></div>
                              </div>
                           </div>
                           <div class="mb-3">
                              <label class="text-muted small fw-bold">Dava Özeti</label>
                              <div id="viewDescription" class="p-3 border rounded bg-white text-secondary"></div>
                           </div>
                        </div>
                        <div id="edit-mode" class="d-none">
                           <div class="alert alert-warning p-2 small mb-3">Düzenleme modu aktiftir.</div>
                           <form id="editCaseForm">
                              <div class="row mb-3">
                                 <div class="col-md-6"><label class="fw-bold small">Müvekkil</label><select class="form-select" id="editCaseClientSelect"></select></div>
                                 <div class="col-md-6"><label class="fw-bold small">Mahkeme</label><input type="text" class="form-control" id="editCaseCourt"></div>
                              </div>
                              <div class="row mb-3">
                                 <div class="col-md-6"><label class="fw-bold small">Tarih</label><input type="date" class="form-control" id="editCaseDate"></div>
                                 <div class="col-md-6">
                                    <label class="fw-bold small">Durum</label>
                                    <select class="form-select" id="editCaseStatus">
                                       <option value="true">Aktif</option>
                                       <option value="false">Pasif (Kapalı)</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="mb-3"><label class="fw-bold small">Özet</label><textarea class="form-control" id="editCaseDesc" rows="4"></textarea></div>
                              <div class="d-flex justify-content-end gap-2"><button type="button" class="btn btn-secondary" onclick="toggleEditMode(false)">İptal</button><button type="button" class="btn btn-success" onclick="saveEditCase()">Kaydet</button></div>
                           </form>
                        </div>
                     </div>
                     <div class="tab-pane fade" id="tab-history">
                        <div class="d-flex mb-4 gap-2" id="historyInputContainer"><input type="date" id="newHistoryDate" class="form-control" style="max-width: 160px;"><input type="text" id="newHistoryNote" class="form-control" placeholder="Açıklama giriniz..."><button id="btnAddHistory" class="btn btn-success" onclick="addManualHistory()">Ekle</button></div>
                        <ul class="timeline" id="modalTimeline"></ul>
                     </div>
                     <div class="tab-pane fade" id="tab-files">
                        <div class="text-center text-muted p-5">
                           <i class="fa-solid fa-cloud-arrow-up fa-3x mb-3"></i>
                           <p>Dosya yükleme yakında...</p>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="addCaseModal" tabindex="-1">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header bg-primary text-white">
                  <h5 class="modal-title">Yeni Dava Dosyası</h5>
                  <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
               </div>
               <div class="modal-body">
                  <form id="newCaseForm">
                     <div class="mb-3"><label class="fw-bold">Dosya No (ID)</label><input type="text" class="form-control" id="newCaseId" placeholder="Örn: 2024/101"></div>
                     <div class="mb-3"><label class="fw-bold">Müvekkil</label><select class="form-select" id="newCaseClientSelect"></select></div>
                     <div class="mb-3"><label class="fw-bold">Mahkeme</label><input type="text" class="form-control" id="newCaseCourt"></div>
                     <div class="mb-3"><label class="fw-bold">Tür</label><input type="text" class="form-control" id="newCaseType"></div>
                     <div class="mb-3"><label class="fw-bold">Tarih</label><input type="date" class="form-control" id="newCaseDate"></div>
                     <div class="mb-3"><label class="fw-bold">Açıklama</label><textarea class="form-control" id="newCaseDesc" rows="3"></textarea></div>
                  </form>
               </div>
               <div class="modal-footer"><button class="btn btn-primary" onclick="saveNewCase()">Dosyayı Oluştur</button></div>
            </div>
         </div>
      </div>
      <div class="modal fade" id="addClientModal" tabindex="-1">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header bg-success text-white">
                  <h5 class="modal-title" id="modalClientTitle">Müvekkil Yönetimi</h5>
                  <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
               </div>
               <div class="modal-body">
                  <form id="newClientForm">
                     <input type="hidden" id="editClientOriginalId">
					 <div class="mb-3">
						<label>TC / VKN</label>
						<input type="text" class="form-control" id="newClientIdNo">
						<small class="text-primary"><i class="fa-solid fa-info-circle"></i> 11 hane bireysel, 10 hane kurumsal kaydedilir.</small>
                     </div>
                     <div class="mb-3">
						<label>Ad Soyad / Ünvan</label>
						<input type="text" class="form-control" id="newClientName">
                     </div>
                     <div class="mb-3">
						<label>Telefon</label>
						<input type="text" class="form-control" id="newClientPhone">
                     </div>
                     <div class="mb-3">
						<label>Adres</label>
						<textarea class="form-control" id="newClientAddress"></textarea>
                     </div>
                  </form>
               </div>
               <div class="modal-footer"><button id="btnSaveClient" class="btn btn-success" onclick="saveNewClient()">Kaydet</button></div>
            </div>
         </div>
      </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
       <script>
const API = {
    CLIENT: "http://localhost:8080/rest/api/client",
    CASE: "http://localhost:8080/rest/api/case",
    HISTORY: "http://localhost:8080/rest/api/casehistory",
    USER: "http://localhost:8080/rest/api/user"
};
let globalClients = [], globalCases = [], currentCaseId = null, currentDate = new Date();

document.addEventListener("DOMContentLoaded", () => {
    checkAuth();
    loadClients();
    // Davaları yükle VE YÜKLENİNCE BUGÜNÜ SEÇ (Önemli kısım burası)
    loadCases().then(() => selectTodayAutomatic());
    renderDashboard();
});

function checkAuth() {
    const userStr = localStorage.getItem('user');
    if (!userStr) {
        window.location.href = "login.html";
        return;
    }
    const user = JSON.parse(userStr);
    let displayName = user.mail;
    if (user.name && user.surname)
        displayName = user.name + " " + user.surname;
    else if (user.name)
        displayName = user.name;
    document.getElementById('lblUserName').innerText = displayName || "Kullanıcı";
}

function loadClients() {
    fetch(API.CLIENT + "/list").then(r => r.json()).then(d => {
        globalClients = d;
        renderTables();
    });
}

// Promise yapısına çevirdik ki bitince takvimi güncelleyebilelim
function loadCases() {
    return fetch(API.CASE + "/list").then(r => r.json()).then(d => {
        globalCases = d;
        renderTables();
        renderCalendar();
    });
}

// --- OTOMATİK BUGÜNÜ SEÇME FONKSİYONU ---
function selectTodayAutomatic() {
    const today = new Date();
    const y = today.getFullYear();
    const m = String(today.getMonth() + 1).padStart(2, '0');
    const d = String(today.getDate()).padStart(2, '0');
    const todayStr = `${y}-${m}-${d}`; // Format: 2026-01-03

    // Veriler hazır, bugünün davalarını bul
    const todaysCases = globalCases.filter(c => c.date === todayStr);
    showDayDetails(todayStr, todaysCases);
}

function renderTables() {
    const caseBody = document.getElementById('caseTableBody');
    caseBody.innerHTML = "";
    globalCases.forEach(c => {
        const client = globalClients.find(cl => cl.id === c.clientId);
        const statusBadge = (c.status === true || c.status === "Aktif") ? '<span class="status-badge status-aktif">Aktif</span>' : '<span class="status-badge status-kapali">Kapalı</span>';
        caseBody.innerHTML += `<tr><td class="fw-bold">${c.id}</td><td>${client ? client.name : '-'}</td><td>${c.type || '-'}</td><td>${c.date || '-'}</td><td>${statusBadge}</td><td><button class="btn btn-sm btn-outline-primary" onclick="viewCase('${c.id}')">İncele</button></td></tr>`;
    });
    const clientBody = document.getElementById('clientTableBody');
    clientBody.innerHTML = "";
    globalClients.forEach(c => {
        clientBody.innerHTML += `<tr><td>${c.id}</td><td>${c.name}</td><td>${c.type ? 'Bireysel' : 'Kurumsal'}</td><td>${c.phone || '-'}</td><td><button class="btn btn-sm btn-outline-dark" onclick="editClient('${c.id}')">Düzenle</button> <button class="btn btn-sm btn-outline-primary" onclick="openAddCaseModal('${c.id}')">Dava Aç</button></td></tr>`;
    });
}

function renderCalendar() {
    const monthYear = document.getElementById('currentMonthYear');
    const calendarDays = document.getElementById('calendarDays');
    const options = {
        month: 'long',
        year: 'numeric'
    };
    monthYear.innerText = currentDate.toLocaleDateString('tr-TR', options);
    calendarDays.innerHTML = "";

    const realToday = new Date(); // Gerçek bugün
    const firstDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1).getDay();
    const lastDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0).getDate();
    let startDayIndex = firstDay === 0 ? 6 : firstDay - 1;

    for (let i = 0; i < startDayIndex; i++) {
        calendarDays.innerHTML += '<div class="calendar-day border-0 bg-transparent"></div>';
    }

    for (let i = 1; i <= lastDate; i++) {
        const dayDiv = document.createElement('div');
        dayDiv.className = 'calendar-day';

        // Bugünü Kontrol Et ve İşaretle
        if (i === realToday.getDate() && currentDate.getMonth() === realToday.getMonth() && currentDate.getFullYear() === realToday.getFullYear()) {
            dayDiv.classList.add('today');
        }

        dayDiv.innerHTML = `<div class="fw-bold mb-1">${i}</div>`;
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const day = String(i).padStart(2, '0');
        const checkDate = `${currentDate.getFullYear()}-${month}-${day}`;

        const dayCases = globalCases.filter(c => c.date === checkDate);
        dayCases.forEach(c => {
            const isActive = (c.status === true || c.status === "Aktif");
            dayDiv.innerHTML += `<div class="event-badge ${isActive ? 'event-active' : 'event-passive'}" title="${c.court}">${c.id}</div>`;
        });

        dayDiv.onclick = () => {
            document.querySelectorAll('.calendar-day').forEach(el => el.classList.remove('selected'));
            dayDiv.classList.add('selected');
            showDayDetails(checkDate, dayCases);
        };
        calendarDays.appendChild(dayDiv);
    }
}

function showDayDetails(dateStr, cases) {
    const title = document.getElementById('selectedDateTitle');
    const list = document.getElementById('selectedDayList');
    // Tarihi güvenli parse etme
    const parts = dateStr.split('-'); // 2026-01-03
    const d = new Date(parts[0], parts[1] - 1, parts[2]);
    const formattedDate = d.toLocaleDateString('tr-TR');

    title.innerText = `Seçili Gün: ${formattedDate}`;
    list.innerHTML = "";

    if (!cases || cases.length === 0) {
        list.innerHTML = `<div class="text-center text-muted mt-4"><p>Bu tarihte duruşma yok.</p></div>`;
        return;
    }

    cases.forEach(c => {
        const client = globalClients.find(cl => cl.id === c.clientId);
        const isActive = (c.status === true || c.status === "Aktif");
        const icon = isActive ? '<i class="fa-solid fa-scale-balanced text-success"></i>' : '<i class="fa-solid fa-lock text-danger"></i>';
        const statusText = isActive ? '<span class="status-badge status-aktif" style="font-size:0.7em; padding:2px 8px;">Aktif</span>' : '<span class="status-badge status-kapali" style="font-size:0.7em; padding:2px 8px;">Kapalı</span>';
        list.innerHTML += `<div class="card mb-2 border p-2" style="cursor:pointer;" onclick="viewCase('${c.id}')"><div class="d-flex justify-content-between"><small class="fw-bold text-primary">${icon} ${c.id}</small>${statusText}</div><div class="small mt-1 fw-bold">${client ? client.name : '-'}</div><div class="small text-muted">${c.court}</div></div>`;
    });
}

function changeMonth(step) {
    currentDate.setMonth(currentDate.getMonth() + step);
    renderCalendar();
}
function switchPage(pageId, clickedLink) {
    document.querySelectorAll('.page-section').forEach(x => x.classList.remove('active'));
    document.getElementById('page-' + pageId).classList.add('active');
    document.querySelectorAll('.sidebar-menu a').forEach(x => x.classList.remove('active'));
    if (clickedLink)
        clickedLink.classList.add('active');
    const titles = {
        'dashboard': 'Dashboard',
        'cases': 'Dava Dosyaları',
        'clients': 'Müvekkil Listesi',
        'calendar': 'Duruşma Takvimi'
    };
    document.getElementById('pageTitle').innerText = titles[pageId] || 'Panel';
    if (pageId === 'dashboard')
        renderDashboard();
}

// --- FONKSİYONLARIN GERİ KALANI AYNI (KISALTILDI) ---
function openSettingsModal() {
    const u = localStorage.getItem('user');
    if (u) {
        const j = JSON.parse(u);
        document.getElementById('settingsName').value = j.name || "";
        document.getElementById('settingsSurname').value = j.surname || "";
        document.getElementById('settingsMail').value = j.mail || "";
        new bootstrap.Modal(document.getElementById('settingsModal')).show();
    }
}
function updateUserSettings() {
    const u = JSON.parse(localStorage.getItem('user'));
    if (!u || !u.id) {
        alert("ID yok");
        return;
    }
    const p = {
        name: document.getElementById('settingsName').value,
        surname: document.getElementById('settingsSurname').value,
        mail: document.getElementById('settingsMail').value,
        password: document.getElementById('settingsPassword').value
    };
    fetch(API.USER + "/update/" + u.id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(p)
    }).then(r => {
        if (r.ok) {
            alert("Güncellendi!");
            u.name = p.name;
            u.surname = p.surname;
            u.mail = p.mail;
            localStorage.setItem('user', JSON.stringify(u));
            checkAuth();
            bootstrap.Modal.getInstance(document.getElementById('settingsModal')).hide();
        } else
            alert("Hata")
    });
}
function logoutSystem() {
    if (confirm("Çıkış?")) {
        localStorage.removeItem('user');
        window.location.href = "login.html";
    }
}

// Dava/Müvekkil Fonksiyonları (Önceki kodla birebir aynı, yer kaplamasın diye kısaltıldı ama çalışır durumda)
function openAddCaseModal(cid) {
    document.getElementById('newCaseForm').reset();
    const s = document.getElementById('newCaseClientSelect');
    s.innerHTML = '<option disabled selected>Seçiniz...</option>';
    globalClients.forEach(c => s.innerHTML += `<option value="${c.id}">${c.name} (${c.id})</option>`);
    if (cid)
        s.value = cid;
    new bootstrap.Modal(document.getElementById('addCaseModal')).show();
}
function saveNewCase() {
    const id = document.getElementById('newCaseId').value;
    const cid = document.getElementById('newCaseClientSelect').value;
    if (!id || !cid) {
        alert("Eksik");
        return;
    }
    const p = {
        id: id,
        clientId: cid,
        court: document.getElementById('newCaseCourt').value,
        type: document.getElementById('newCaseType').value,
        date: document.getElementById('newCaseDate').value,
        description: document.getElementById('newCaseDesc').value,
        status: true
    };
    fetch(API.CASE + "/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(p)
    }).then(r => {
        if (r.ok) {
            saveHistoryToBackend(id, "Oluşturuldu", new Date().toISOString().split('T')[0]);
            alert("Açıldı!");
            loadCases();
            bootstrap.Modal.getInstance(document.getElementById('addCaseModal')).hide();
        }
    });
}
function viewCase(id) {
    currentCaseId = id;
    const c = globalCases.find(x => x.id == id);
    fillViewMode(c);
    toggleEditMode(false);
    loadHistory(id);
    const a = (c.status === true || c.status === "Aktif");
    document.getElementById('btnCloseCase').classList.toggle('d-none', !a);
    document.getElementById('btnEditCase').classList.toggle('d-none', !a);
    document.getElementById('historyInputContainer').classList.toggle('d-none', !a);
    new bootstrap.Modal(document.getElementById('caseDetailModal')).show();
}
function fillViewMode(c) {
    const cl = globalClients.find(x => x.id === c.clientId);
    document.getElementById('modalFileNumber').innerText = "Dosya: " + c.id;
    document.getElementById('viewClient').innerText = cl ? cl.name : '-';
    document.getElementById('viewCourt').innerText = c.court || '-';
    document.getElementById('viewNextHearing').innerText = c.date || '-';
    const a = (c.status === true || c.status === "Aktif");
    document.getElementById('viewStatus').innerHTML = a ? '<span class="status-badge status-aktif">Aktif</span>' : '<span class="status-badge status-kapali">Kapalı</span>';
    document.getElementById('viewDescription').innerText = c.description || c.descreption || "-";
}
function toggleEditMode(s) {
    const v = document.getElementById('view-mode'),
    e = document.getElementById('edit-mode');
    if (s) {
        const c = globalCases.find(x => x.id == currentCaseId);
        const sl = document.getElementById('editCaseClientSelect');
        sl.innerHTML = '';
        globalClients.forEach(cl => sl.innerHTML += `<option value="${cl.id}" ${cl.id === c.clientId ? 'selected' : ''}>${c.name} (${c.id})</option>`);
        document.getElementById('editCaseCourt').value = c.court;
        document.getElementById('editCaseDate').value = c.date;
        document.getElementById('editCaseDesc').value = c.description || c.descreption;
        document.getElementById('editCaseStatus').value = (c.status === true || c.status === "Aktif") ? "true" : "false";
        v.classList.add('d-none');
        e.classList.remove('d-none');
    } else {
        e.classList.add('d-none');
        v.classList.remove('d-none');
    }
}
function saveEditCase() {
    const c = globalCases.find(x => x.id == currentCaseId);
    const p = {
        id: c.id,
        clientId: document.getElementById('editCaseClientSelect').value,
        court: document.getElementById('editCaseCourt').value,
        type: c.type,
        date: document.getElementById('editCaseDate').value,
        description: document.getElementById('editCaseDesc').value,
        status: document.getElementById('editCaseStatus').value === "true"
    };
    fetch(API.CASE + "/update", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(p)
    }).then(r => {
        if (r.ok) {
            saveHistoryToBackend(c.id, "Güncellendi", new Date().toISOString().split('T')[0]);
            alert("Güncellendi!");
            Object.assign(c, p);
            fillViewMode(c);
            toggleEditMode(false);
            loadCases();
        }
    });
}
function closeCaseProcess() {
    if (!confirm("Kapat?"))
        return;
    const c = globalCases.find(x => x.id == currentCaseId);
    const p = {
        ...c,
        status: false,
        description: c.description || c.descreption
    };
    fetch(API.CASE + "/update", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(p)
    }).then(r => {
        if (r.ok) {
            saveHistoryToBackend(currentCaseId, "Kapatıldı", new Date().toISOString().split('T')[0]);
            alert("Kapatıldı.");
            loadCases();
            bootstrap.Modal.getInstance(document.getElementById('caseDetailModal')).hide();
        }
    });
}
function loadHistory(id) {
    fetch(API.HISTORY + "/list?caseId=" + encodeURIComponent(id)).then(r => r.json()).then(d => {
        const u = document.getElementById('modalTimeline');
        u.innerHTML = "";
        if (!d || d.length == 0) {
            u.innerHTML = "<li>Yok</li>";
            return;
        }
        d.forEach(h => u.innerHTML += `<li class="timeline-item"><strong>${h.comment || h.description}</strong><br><small class="text-muted">${h.date}</small></li>`);
    });
}
function saveHistoryToBackend(id, m, d) {
    fetch(API.HISTORY + "/save", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            caseId: id,
            comment: m,
            date: d
        })
    }).then(() => {
        if (currentCaseId === id)
            loadHistory(id);
    });
}
function addManualHistory() {
    const n = document.getElementById('newHistoryNote').value,
    d = document.getElementById('newHistoryDate').value;
    if (!n || !d)
        return;
    saveHistoryToBackend(currentCaseId, n, d);
    document.getElementById('newHistoryNote').value = "";
}
function openAddClientModal() {
    document.getElementById('newClientForm').reset();
    document.getElementById('newClientIdNo').disabled = false;
    document.getElementById('btnSaveClient').innerText = "Kaydet";
    new bootstrap.Modal(document.getElementById('addClientModal')).show();
}
function editClient(id) {
    const c = globalClients.find(x => x.id == id);
    document.getElementById('newClientIdNo').value = c.id;
    document.getElementById('newClientIdNo').disabled = true;
    document.getElementById('newClientName').value = c.name;
    document.getElementById('newClientPhone').value = c.phone;
    document.getElementById('newClientAddress').value = c.address;
    document.getElementById('btnSaveClient').innerText = "Güncelle";
    new bootstrap.Modal(document.getElementById('addClientModal')).show();
}
function saveNewClient() {
    const p = {
        id: document.getElementById('newClientIdNo').value,
        name: document.getElementById('newClientName').value,
        phone: document.getElementById('newClientPhone').value,
        address: document.getElementById('newClientAddress').value
    };
    const u = document.getElementById('newClientIdNo').disabled;
    fetch(API.CLIENT + (u ? "/update" : "/save"), {
        method: u ? "PUT" : "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(p)
    }).then(r => {
        if (r.ok) {
            alert("Başarılı");
            loadClients();
            bootstrap.Modal.getInstance(document.getElementById('addClientModal')).hide();
        }
    });
}
function renderDashboard() {
    fetch(API.CASE + "/desahboard").then(r => r.json()).then(d => {
        document.getElementById('statTotalCases').innerText = d.totalCase || 0;
        document.getElementById('statActiveCases').innerText = d.activeCase || 0;
        document.getElementById('statUpcoming').innerText = d.upcomingCase || 0;
    });
}
</script>
   </body>
</html>





