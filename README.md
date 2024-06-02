A noob plugin to set spawn/hub according to the world (Just to get back into the java)

## Commands
- `spawn` - "A spawn command"
- `hub` - "A hub command"
- `/ctsetspawn <world> <TpToWorld> <x> <y> <z> [yaw] [pitch]` - Set the spawn of a world
- `/ctsethub <world> <TpToWorld> <x> <y> <z> [yaw] [pitch]` - Set the hub of a world
- `/ctdisable <type> <world>` - Disable a spawn/hub commands of an world
- `/ctenable <type> <world>` - Enable a spawn/hub commands of an world
- `/ctcooldown <type> <world> <cooldown>` - Set cooldown before teleport


## Config Exemple
```yml
prefix: "&l[&4Custom&cTeleport&f]&f"
spawn:
  world:
    disable: true
    cooldownBeforeTp: 5
    tpToWorld: "world"
    x: 200.5
    y: 71
    z: 41.5
    yaw: 0
    pitch: 0
  tEst:
    tpToWorld: "world"
    cooldownBeforeTp: 3
    x: 211
    y: 71
    z: 41
```

## Credits
[@Roby360](https://github.com/Roby360) 
I see him stream and i decited to do same thing 
