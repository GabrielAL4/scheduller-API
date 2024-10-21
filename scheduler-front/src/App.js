import React from 'react';
import QueryForm from './components/QueryForm'; // Importe o componente do formulário

const App = () => {
  return (
    <div className="App">
      <h1>Agendador de Queries</h1>
      <QueryForm /> {/* Use o componente do formulário */}
    </div>
  );
};

export default App;
