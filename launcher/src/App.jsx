import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainMenu from './pages/MainMenu';
import Multiplayer from './pages/Multiplayer';
import Settings from './pages/Settings';
import AntiCheatBypass from './pages/AntiCheatBypass';
import { motion, AnimatePresence } from 'framer-motion';
import './App.css';

function App() {
  const [currentPage, setCurrentPage] = useState('menu');
  const [account, setAccount] = useState(null);

  useEffect(() => {
    const savedAccount = localStorage.getItem('minecraftAccount');
    if (savedAccount) {
      setAccount(JSON.parse(savedAccount));
    }
  }, []);

  return (
    <div className="app-container">
      <AnimatePresence mode="wait">
        {currentPage === 'menu' && (
          <MainMenu key="menu" onNavigate={setCurrentPage} account={account} />
        )}
        {currentPage === 'multiplayer' && (
          <Multiplayer key="multiplayer" onNavigate={setCurrentPage} account={account} />
        )}
        {currentPage === 'bypass' && (
          <AntiCheatBypass key="bypass" onNavigate={setCurrentPage} />
        )}
        {currentPage === 'settings' && (
          <Settings key="settings" onNavigate={setCurrentPage} />
        )}
      </AnimatePresence>
    </div>
  );
}

export default App;
