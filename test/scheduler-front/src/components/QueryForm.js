import React, { useState } from 'react';

const QueryForm = () => {
  const [title, setTitle] = useState('');
  const [query, setQuery] = useState('');
  const [database, setDatabase] = useState('');
  const [periodicity, setPeriodicity] = useState('');
  const [path, setPath] = useState('');

  const handleFolderSelect = async () => {
    const folderPath = await window.electron.openFolderDialog();
    if (folderPath) {
      setPath(folderPath); // Define o caminho da pasta escolhida
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aqui você pode adicionar a lógica para enviar a consulta SQL
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Título:</label>
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
      </div>
      <div>
        <label>Query:</label>
        <textarea value={query} onChange={(e) => setQuery(e.target.value)} />
      </div>
      <div>
        <label>Banco de Dados:</label>
        <select value={database} onChange={(e) => setDatabase(e.target.value)}>
          <option value="">Selecione</option>
          <option value="db1">Banco 1</option>
          <option value="db2">Banco 2</option>
          <option value="db3">Banco 3</option>
          <option value="db4">Banco 4</option>
        </select>
      </div>
      <div>
        <label>Periodicidade:</label>
        <select value={periodicity} onChange={(e) => setPeriodicity(e.target.value)}>
          <option value="daily">Diariamente</option>
          <option value="weekly">Semanalmente</option>
          <option value="monthly">Mensalmente</option>
        </select>
      </div>
      <div>
        <label>Caminho:</label>
        <input type="text" value={path} readOnly />
        <button type="button" onClick={handleFolderSelect}>Escolher Pasta</button>
      </div>
      <button type="submit">Enviar</button>
    </form>
  );
};

export default QueryForm;
