# Seeker's Pool — Servlet / JDBC 個人作品 / Personal Project

Seeker's Pool 是一個求職網站的個人開發作品，提供求職會員註冊與登入、個人資料管理、職缺與文章收藏、應徵紀錄，以及透過電子郵件進行的面試邀請與時間確認。本版本以 **Java Servlet、JDBC 與 MVC 分層** 為主要練習方向，著重 HTTP 請求、Session、資料存取與業務流程串接。

Seeker's Pool is a personal job-platform project featuring registration and login, profile management, saved jobs and articles, job applications, and email-based interview invitations and confirmations. This version focuses on **Java Servlets, JDBC, and MVC-style layering**, emphasizing HTTP requests, sessions, persistence, and workflow integration.

## 核心功能 / Key Features

- **會員管理**：註冊、登入、修改基本資料與上傳照片。<br>**Member management**: Registration, login, profile updates, and photo uploads.
- **登入狀態**：Servlet Filter 檢查登入，HttpSession 保存會員狀態。<br>**Session handling**: Servlet Filter login checks and HttpSession member state.
- **信箱驗證**：寄送驗證郵件，並透過 Redis 暫存驗證碼。<br>**Email verification**: Email verification with temporary Redis codes.
- **收藏與應徵**：管理職缺、文章收藏與應徵紀錄。<br>**Favorites and applications**: Manage saved jobs, articles, and application records.
- **面試邀請**：以郵件提供候選時段與確認連結。<br>**Interview invitations**: Send proposed interview slots and confirmation links by email.

## 架構 / Architecture

`Servlet → Service → DAO → Database`

Servlet 處理 HTTP 請求與回應，Service 協調業務流程，DAO 處理資料存取；共用工具支援 Redis、資料庫連線與寄信功能。

Servlets handle HTTP requests and responses, services coordinate workflows, and DAOs handle persistence. Utilities support Redis, database connections, and email delivery.

## 技術棧 / Technology Stack

- Java 17、Servlet、JDBC、MVC、HttpSession、Servlet Filter
- Maven、WAR（`pom.xml` 使用 Spring Boot 2.7.0 parent）
- HTML、CSS、JavaScript、jQuery、AJAX
- MySQL、Redis、HikariCP、JavaMail

本版本主要以 Servlet／JDBC 實作；POM 雖然包含 Spring Boot、JPA 等套件，不表示業務功能均採用 Spring MVC 或 JPA。

The primary implementation uses Servlets and JDBC. The presence of Spring Boot and JPA dependencies does not mean all business features use Spring MVC or JPA.

## 本機執行 / Running Locally

請準備 Java 17、Maven、MySQL、Redis 與測試郵件環境，依環境調整 `src/main/resources/Hikari.properties`、`jedis.properties` 以及郵件設定，準備對應資料庫結構後，以 WAR 方式部署至相容的 Servlet 容器。本 Repository 不保證在全新環境中一鍵啟動。

Prepare Java 17, Maven, MySQL, Redis, and a test email environment. Configure `src/main/resources/Hikari.properties`, `jedis.properties`, and email settings; supply the required database schema, then deploy the WAR to a compatible Servlet container. One-command startup on a fresh machine is not guaranteed.

## 限制與安全 / Limitations and Security

這是個人學習作品，登入驗證、檔案上傳、郵件連結與設定需經安全審查後才能考慮正式部署。請勿使用真實個資或正式憑證測試，也不要直接對公網提供服務。

This is a personal learning project. Authentication, file uploads, email links, and configuration require security review before production deployment. Avoid real personal data, production credentials, and direct public exposure.

## 相關作品 / Related Project

Spring Boot／Spring Data JPA 版本 / Spring Boot and Spring Data JPA version: [Seeker_Pool_Fong_Framework](https://github.com/Adam-Chung/Seeker_Pool_Fong_Framework).


## 補充
#### 功能簡報說明：https://pse.is/559wbx
#### Feature Presentation: https://pse.is/559wbx

#### 功能畫面演示：https://yt1.piee.pw/5522yj
#### Feature Demo Video: https://yt1.piee.pw/5522yj
