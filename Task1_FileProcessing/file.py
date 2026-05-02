def create_large_files():
    for i in range(1, 6):  # 5 files
        filename = f"file_{i}.txt"
        
        with open(filename, "w") as f:
            for j in range(10000):  # 10,000 lines
                f.write(f"This is line number {j} in {filename}\n")
    
    print("Files created successfully!")

create_large_files()