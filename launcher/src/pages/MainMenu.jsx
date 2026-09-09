import React from 'react';
import { motion } from 'framer-motion';
import '../styles/MainMenu.css';

const MainMenu = ({ onNavigate, account }) => {
  const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        staggerChildren: 0.1,
        delayChildren: 0.2
      }
    },
    exit: { opacity: 0, transition: { duration: 0.3 } }
  };

  const itemVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: {
      opacity: 1,
      y: 0,
      transition: { duration: 0.5, ease: 'easeOut' }
    }
  };

  return (
    <motion.div
      className="page-container main-menu"
      variants={containerVariants}
      initial="hidden"
      animate="visible"
      exit="exit"
    >
      {account && <div className="account-banner">👤 {account.username}</div>}

      <motion.div className="logo-section" variants={itemVariants}>
        <div className="logo-title">MOONDLC</div>
        <div className="logo-subtitle">Unleash the full potential of your game.</div>
      </motion.div>

      <motion.div className="menu-buttons" variants={containerVariants}>
        <motion.button
          className="btn"
          onClick={() => onNavigate('singleplayer')}
          variants={itemVariants}
          whileHover={{ scale: 1.02 }}
          whileTap={{ scale: 0.98 }}
        >
          Single Player
        </motion.button>
        <motion.button
          className="btn"
          onClick={() => onNavigate('multiplayer')}
          variants={itemVariants}
          whileHover={{ scale: 1.02 }}
          whileTap={{ scale: 0.98 }}
        >
          Multi Player
        </motion.button>
        <motion.button
          className="btn"
          onClick={() => onNavigate('bypass')}
          variants={itemVariants}
          whileHover={{ scale: 1.02 }}
          whileTap={{ scale: 0.98 }}
          style={{ borderColor: '#00ff88', color: '#00ff88' }}
        >
          🔓 Anti-Cheat Bypass
        </motion.button>
        <motion.button
          className="btn"
          onClick={() => onNavigate('settings')}
          variants={itemVariants}
          whileHover={{ scale: 1.02 }}
          whileTap={{ scale: 0.98 }}
        >
          Settings
        </motion.button>
        <motion.button
          className="btn btn-secondary"
          onClick={() => window.close()}
          variants={itemVariants}
          whileHover={{ scale: 1.02 }}
          whileTap={{ scale: 0.98 }}
        >
          Quit
        </motion.button>
      </motion.div>
    </motion.div>
  );
};

export default MainMenu;
