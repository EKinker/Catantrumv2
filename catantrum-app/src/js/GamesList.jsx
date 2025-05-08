import React, { useState } from 'react';
import '../css/GamesList.css';

const GameList = ({ games }) => {
    const [selectedGame, setSelectedGame] = useState(null);

    const handleGameClick = (game) => {
        setSelectedGame(game);
    };

    const closeModal = () => {
        setSelectedGame(null);
    };

    return (
        <div>
            <ul className="game-list">
                {games.map((game) => (
                    <li
                        key={game.id}
                        className="game-item"
                        onClick={() => handleGameClick(game)}
                    >
                        <strong>{game.name}</strong>
                    </li>
                ))}
            </ul>

            {selectedGame && (
                <div className="modal-overlay" onClick={closeModal}>
                    <div className="modal" onClick={(e) => e.stopPropagation()}>
                        <h2>{selectedGame.name}</h2>
                        <p>Description: {selectedGame.description || 'No description available.'}</p>
                        <p>Players: {selectedGame.minPlayers}-{selectedGame.maxPlayers} players</p>
                        <p>Ages: {selectedGame.minAge}{selectedGame.maxAge ? `-${selectedGame.maxAge}` : '+'}</p>
                        <p>Time: {selectedGame.minTime}{selectedGame.maxTime ? `-${selectedGame.maxTime}` : '+'} minutes</p>
                        <p>Genre: {selectedGame.genre || 'Unknown'}</p>
                        <p>Publisher: {selectedGame.publisher || 'N/A'}</p>
                        <button onClick={closeModal}>Close</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default GameList;
