import * as React from "react";
import * as d3 from "d3";
import * as topojson from "topojson-client";
import { TransitionGroup, CSSTransition } from "react-transition-group";
import { IMapProps, IMapState } from "../../types/MapTypes";
import { State } from "./State";
import * as stateIds from "../../data/us-states-ids.json";

export class Map extends React.Component<IMapProps, IMapState> {

    private californiaOnly = "California";

    constructor(props: any) {
        super(props);
        
        this.generateMap = this.generateMap.bind(this);
        this.generatePath = this.generatePath.bind(this);

        this.state = {
            states: stateIds,
            maps: null,
            stations: []
        };
    }

    public componentDidMount(): void {
        this.setState({maps: null});
    }

    public componentDidUpdate(): void {

    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        this.setState({maps: nextProps.maps, stations: nextProps.stations});
    }

    private generatePath(geoPath: any, data: any): JSX.Element | undefined {
        const mapType = "US";

        return (
            <TransitionGroup component={null}>
                {data.map((feature: any, i: number) => {
                    // const breaks = this.getChoroplethBreaks();
                    const fill = "#0de298";
                    const path = geoPath(feature);

                    if (this.state.states[feature.id].name === this.californiaOnly) {
                        return (
                            <CSSTransition
                                key={i}
                                classNames={`state-transition-${i}`}
                                appear={true}
                                timeout={5000}
                            >
    
                                <State
                                    mapType={mapType}
                                    stateName={feature.properties.stateName}
                                    stations={this.state.stations}
                                    path={path}
                                    feature={feature}
                                    i={i}
                                    fill={fill}
                                    radius={40}
                                />
    
                            </CSSTransition>
                        );
                    } else {
                        return undefined;
                    }
                })}
            </TransitionGroup>
        );
    }

    public generateMap(): JSX.Element | undefined {
        if (this.state.maps) {
            const maps = this.state.maps;
            const data = topojson.feature(maps, maps.objects.states)["features"];
            return this.generatePath(d3.geoPath(), data);
        } else {
            return (<div>test</div>);
        }
    }

    public render(): JSX.Element {
        return (
            <div className="map-container">
                <svg
                    className={`map US`}
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 400 600"
                >
                    {this.generateMap()}
                </svg>
            </div>
        );
    }

}