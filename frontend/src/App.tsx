import { useState } from "react";
import logoFuria from "./assets/logo-furia.svg"; // Importando a logo
import axios from "axios"; // Importando axios para requisição HTTP
import { marked } from "marked"; // Importando marked para renderizar Markdown

function App() {
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState<
    { text: string; sender: "user" | "bot" }[]
  >([]);
  const [] = useState(false); // Adiciona estado para controle de loading

  const handleSend = async () => {
    if (message.trim() === "") return;

    // 1. Adiciona a mensagem do usuário ao histórico imediatamente
    setMessages((prev) => [...prev, { text: message, sender: "user" }]);
    setMessage("");

    // 2. Adiciona uma mensage'm temporária de loading do bot
    const loadingMessage: { text: string; sender: "user" | "bot" } = {
      text: "loading",
      sender: "bot",
    };
    setMessages((prev) => [...prev, loadingMessage]);

    try {
      // 4. Envia a mensagem para o backend para o Gemini API
      const response = await axios.post(`${import.meta.env.VITE_API_URL}`, {
        message,
      });

      // 5. Recebe a resposta do bot da API Gemini
      const botMessage =
        response.data || "Erro: A resposta do bot não foi encontrada";

      // 6. Substitui a mensagem de loading pela resposta do bot
      setMessages((prev) => {
        const updatedMessages = [...prev];
        updatedMessages[updatedMessages.length - 1] = {
          text: botMessage,
          sender: "bot",
        };
        return updatedMessages;
      });
    } catch (error) {
      console.error("Erro ao obter resposta da API Gemini:", error);

      // Substitui a mensagem de loading por uma mensagem de erro
      setMessages((prev) => {
        const updatedMessages = [...prev];
        updatedMessages[updatedMessages.length - 1] = {
          text: "Erro ao obter resposta do bot, tente novamente.",
          sender: "bot",
        };
        return updatedMessages;
      });
    } finally {
      // 7. Remove a mensagem de loading após 2 segundos
      setTimeout(() => {
        setMessages((prev) => prev.filter((msg) => msg.text !== "loading"));
      }, 2000);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      handleSend();
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4">
      {/* Logo da FURIA */}
      <div className="flex justify-center p-4 absolute top-6 pointer-events-none">
        <img src={logoFuria} alt="FURIA Logo" className="h-12" />
      </div>

      <div className="bg-white border border-gray-300 rounded-lg flex flex-col w-full max-w-2xl h-[80vh] shadow-md">
        {/* Histórico de mensagens */}
        <div className="flex-1 overflow-y-auto p-4 text-black space-y-4">
          {messages.length === 0 ? (
            <p className="text-center text-gray-400 mt-4">No messages yet.</p>
          ) : (
            messages.map((msg, idx) => (
              <div
                key={idx}
                className={`flex items-start ${
                  msg.sender === "user" ? "flex-row-reverse" : ""
                }`}
              >
                {/* Avatar */}
                <div
                  className={`w-10 h-10 rounded-full flex items-center justify-center text-black text-sm font-bold ${
                    msg.sender === "user" ? "bg-blue-500" : "bg-gray-300"
                  }`}
                >
                  {msg.sender === "user" ? (
                    "U"
                  ) : (
                    <img
                      src="https://upload.wikimedia.org/wikipedia/pt/f/f9/Furia_Esports_logo.png"
                      alt="FURIA Logo"
                      className="object-contain w-8 h-8 pointer-events-none"
                    />
                  )}
                </div>

                {/* Mensagem */}
                <div
                  className={`p-3 rounded-lg ${
                    msg.sender === "user"
                      ? "bg-blue-500 text-white mr-3"
                      : "bg-gray-100 text-black ml-3"
                  }`}
                >
                  {/* Verifica se a mensagem é de "loading" */}
                  {msg.text === "loading" ? (
                    <div className="flex flex-row gap-2">
                      <div className="w-4 h-4 rounded-full bg-blue-700 animate-bounce"></div>
                      <div className="w-4 h-4 rounded-full bg-blue-700 animate-bounce [animation-delay:-.3s]"></div>
                      <div className="w-4 h-4 rounded-full bg-blue-700 animate-bounce [animation-delay:-.5s]"></div>
                    </div>
                  ) : (
                    <div
                      dangerouslySetInnerHTML={{ __html: marked(msg.text) }}
                    />
                  )}
                </div>
              </div>
            ))
          )}
        </div>

        {/* Área de input */}
        <div className="p-4 border-t border-gray-300 flex">
          <input
            type="text"
            placeholder="Type your message..."
            className="flex-1 rounded border border-gray-300 px-4 py-2 text-black focus:outline-none focus:ring-2 focus:ring-gray-400"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            onKeyDown={handleKeyDown}
          />
          <button
            className="ml-2 border border-black text-black px-4 py-2 rounded hover:bg-gray-200 font-semibold"
            onClick={handleSend}
          >
            Send
          </button>
        </div>
      </div>
      <p className="fixed bottom-2 right-2 text-sm text-black opacity-70">
        Dados atualizados até 2023, a IA pode fornecer informações incorretas
      </p>
    </div>
  );
}

export default App;
