import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './themed-bootstrap.scss'
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { DarkModeContextWrapper } from './contexts/DarkModeContext'
import { AuthContextProvider } from './contexts/AuthContext';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ToyStoreContextProvider } from './contexts/ToyStoreContext';
import 'bootstrap/dist/css/bootstrap.min.css';
const root = ReactDOM.createRoot(document.getElementById('root'));
const queryClient = new QueryClient();
root.render(
    <QueryClientProvider client={queryClient}>
        <ToyStoreContextProvider>
 <AuthContextProvider>
 <DarkModeContextWrapper>
 <BrowserRouter>
 <App/>
 </BrowserRouter>
 </DarkModeContextWrapper>
 </AuthContextProvider>
 </ToyStoreContextProvider>
 </QueryClientProvider>
);
reportWebVitals();

